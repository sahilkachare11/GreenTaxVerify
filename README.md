# 🌍 GreenTaxVerify

GreenTaxVerify is a smart waste verification Android application designed to streamline and authenticate environmental compliance for residential societies. By leveraging this platform, users can upload reliable, location-tagged photographic proof of their composting efforts, which is then seamlessly verified by a Flask backend to facilitate green tax rebates.

---

## 📖 Description

The platform acts as a crucial bridge between residential societies and civic authorities. 
- **Users** (Residents or Secretaries) can log in and capture live, GPS-tagged photos of their environmental initiatives (such as composters, solar panels, or water recycling setups).
- **The Backend** receives the multipart image uploads and processes the verification.
- Civic authorities can then transparently review and approve the evidence to apply tax deductions fairly.

---

## ✨ Features

- **Role-Based Access**: Dedicated dashboard views depending on your role: **Admin** (Civic Body), **Secretary** (Society Rep), and **Resident**.
- **Live Proof Upload**: Captures images to guarantee authenticity.
- **API Verification**: Robust `Retrofit` networking transmits `Multipart` image data smoothly.
- **Dynamic Leaderboards**: Real-time ranking of residential societies based on historic compliance scores.
- **Interactive UI**: Clean, Material-3-inspired Android layouts showcasing metrics like Green Cover, Trust Scores, and Impact.

---

## 🛠️ Tech Stack

- **Frontend / Mobile**: 
  - Android SDK (Java)
  - XML Layouts (ConstraintLayout, Material 3 Components)
- **Networking / API Client**: 
  - Retrofit2
  - OkHttp3
  - Gson (JSON Processing)
- **Backend / API Server**: 
  - Python (Flask Framework)
  - Werkzeug (Secure File Storage)

---

## 🚀 Setup Instructions

### 1. How to run the Backend (Flask)

1. Ensure you have **Python 3.8+** installed along with `pip`.
2. Open your terminal and navigate to the backend folder:
   ```bash
   cd backend
   ```
3. Install the required libraries:
   ```bash
   pip install Flask Werkzeug
   ```
4. Run the API Server:
   ```bash
   python app.py
   ```
5. The server will start on `http://0.0.0.0:5000/`.

### 2. How to run the Android App

1. Ensure you have **Android Studio** installed.
2. Open the `GreenTaxVerify` folder inside Android Studio.
3. Allow Gradle to sync the required dependencies (such as Retrofit and CircleImageView).
4. **Important**: Ensure your testing device (or emulator) is on the *same local Wi-Fi network* as your backend server. Update the `BASE_URL` inside `app/src/main/java/com/example/greentaxverify/ApiClient.java` to match your computer's local IP address (e.g., `http://192.168.1.5:5000/`).
5. Click **Run** or press `Shift + F10` to build and install the application.

---

## 📂 Folder Structure

```text
GreenTaxVerify/
├── GreenTaxVerify/             # Android Studio Project Root
│   ├── app/src/main/java/      # Core Java Source Code (Activities, API Clients)
│   ├── app/src/main/res/       # UI XML Layouts, Colors, Strings & Menus
│   └── build.gradle.kts        # Android Gradle Build Dependencies
├── backend/                    # Python Backend
│   ├── app.py                  # Main Flask API Application
│   └── temp_uploads/           # Local storage for received image proofs
├── stitch/                     # UI Mockups & HTML prototypes
└── README.md                   # Project Documentation
```

---

## 🔮 Future Improvements

- **AI/ML Automated Verification:** Integrate a Machine Learning image classification model into the Flask backend to autonomously verify the presence and status of composting units.
- **Push Notifications:** Add Firebase Cloud Messaging (FCM) to actively notify users when their proof has been reviewed by authorities.
- **EXIF Data Extraction:** Extract embedded Geolocation and Timestamp metadata directly from the images on the server to cryptographically validate locations against civic zoning data.
- **JWT Authentication:** Implement a robust token-based authorization flow to secure endpoints and user sessions.
