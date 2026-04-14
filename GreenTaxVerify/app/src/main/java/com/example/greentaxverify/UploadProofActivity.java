package com.example.greentaxverify;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadProofActivity extends AppCompatActivity {

    private static final String TAG = "UploadProofActivity";
    private static final int REQUEST_CODE_GALLERY_PERMISSION = 201;
    private static final int REQUEST_CODE_PICK_IMAGE         = 202;

    private static final String GALLERY_PERMISSION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                    ? Manifest.permission.READ_MEDIA_IMAGES
                    : Manifest.permission.READ_EXTERNAL_STORAGE;

    private MaterialToolbar toolbarUpload;
    private ImageView ivCameraPreview;
    private Button btnCapture;
    private BottomNavigationView bottomNavUpload;

    private Uri selectedImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_proof);

        toolbarUpload    = findViewById(R.id.toolbar_upload);
        ivCameraPreview  = findViewById(R.id.iv_camera_preview);
        btnCapture       = findViewById(R.id.btn_capture);
        bottomNavUpload  = findViewById(R.id.bottom_nav_upload);

        setSupportActionBar(toolbarUpload);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnCapture.setOnClickListener(v -> {
            if (selectedImageUri == null) {
                Log.d(TAG, "No image selected, opening gallery.");
                checkPermissionAndOpenGallery();
            } else {
                Log.d(TAG, "Image selected, starting upload process.");
                uploadImage();
            }
        });

        bottomNavUpload.setOnItemSelectedListener(item -> {
            // TODO: handle bottom nav tab changes
            return true;
        });
    }

    private void uploadImage() {
        if (selectedImageUri == null) {
            Log.e(TAG, "uploadImage called but selectedImageUri is null.");
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Uploading...", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Starting image conversion for URI: " + selectedImageUri.toString());

        try {
            File file = getFileFromUri(selectedImageUri);
            if (file == null || !file.exists()) {
                Log.e(TAG, "Failed to create temporary file from URI.");
                Toast.makeText(this, "Could not read image file", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d(TAG, "File created successfully: " + file.getAbsolutePath());

            // Create RequestBody and MultipartBody.Part for retroift
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), reqFile);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseBody> call = apiInterface.verifyProof(imagePart);

            Log.d(TAG, "Executing Retrofit API call to /verify");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    Log.d(TAG, "Received response with code: " + response.code());
                    
                    if (response.isSuccessful() && response.body() != null) {
                        try {
                            String resStr = response.body().string();
                            Log.d(TAG, "Response raw body: " + resStr);
                            
                            JSONObject json = new JSONObject(resStr);
                            String status = json.optString("status");

                            if ("Verified".equals(status)) {
                                Log.d(TAG, "Status is Verified.");
                                Toast.makeText(UploadProofActivity.this, "Verified ✅", Toast.LENGTH_LONG).show();
                            } else {
                                Log.d(TAG, "Status is not Verified: " + status);
                                Toast.makeText(UploadProofActivity.this, "Not Verified ❌", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException | JSONException e) {
                            Log.e(TAG, "Error parsing successful JSON response.", e);
                            Toast.makeText(UploadProofActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e(TAG, "Upload failed with HTTP code: " + response.code());
                        Toast.makeText(UploadProofActivity.this, "Failed with code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.e(TAG, "Retrofit network call failed.", t);
                    Toast.makeText(UploadProofActivity.this, "Upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "Exception encountered during upload setup.", e);
            Toast.makeText(this, "Error processing image for upload", Toast.LENGTH_SHORT).show();
        }
    }

    private File getFileFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        if (inputStream == null) {
            return null;
        }
        File tempFile = File.createTempFile("upload_", ".jpg", getCacheDir());
        tempFile.deleteOnExit();

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } finally {
            inputStream.close();
        }
        return tempFile;
    }

    private void checkPermissionAndOpenGallery() {
        if (ContextCompat.checkSelfPermission(this, GALLERY_PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{GALLERY_PERMISSION},
                    REQUEST_CODE_GALLERY_PERMISSION
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_GALLERY_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, GALLERY_PERMISSION)) {
                Toast.makeText(this, "Gallery permission is needed to upload proof.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission permanently denied. Enable it in App Settings.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openGallery() {
        Log.d(TAG, "Launching gallery selection intent.");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                Log.d(TAG, "Image selected successfully. URI: " + selectedImageUri.toString());
                ivCameraPreview.setImageURI(selectedImageUri);
                // Switch the button behavior to "Upload" mode
                btnCapture.setText("Upload");
                btnCapture.setTextSize(14f);
            } else {
                Log.e(TAG, "Returned intent data is valid but getData() is null.");
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
