import os
from flask import Flask, request, jsonify
from werkzeug.utils import secure_filename

app = Flask(__name__)

# Configure a temporary directory for uploaded images
UPLOAD_FOLDER = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'temp_uploads')
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@app.route('/verify', methods=['POST'])
def verify_proof():
    # Check if the post request has the file part
    if 'image' not in request.files:
        return jsonify({"error": "No image part provided"}), 400
    
    file = request.files['image']
    
    # If the user does not select a file, the browser submits an
    # empty file without a filename.
    if file.filename == '':
        return jsonify({"error": "No selected file"}), 400
    
    if file:
        # Secure the filename and save the file temporarily
        filename = secure_filename(file.filename)
        save_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(save_path)
        print(f"Received and saved image successfully to: {save_path}")
        
        # --- Dummy logic goes here (e.g. Machine Learning inference) ---
        
        # Optionally, you can delete the temporary file after processing
        # os.remove(save_path)

        # Always return 'Verified' 
        return jsonify({"status": "Verified"}), 200

if __name__ == '__main__':
    # Run the app on 0.0.0.0 so it's accessible over the local network (from the Android app)
    # Default port is 5000
    app.run(host='0.0.0.0', port=5000, debug=True)
