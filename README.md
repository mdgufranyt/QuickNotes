# QuickNotes

QuickNotes is a simple, modern note-taking Android app built with Jetpack Compose and Firebase Firestore. It allows users to quickly create, edit, and manage notes with a clean and intuitive interface.

## Features

- **Create, edit, and delete notes**  
  Easily add new notes or update and remove existing ones.

- **Realtime synchronization**  
  Notes are synced in realtime using Firebase Firestore.

- **Modern UI**  
  Built with Jetpack Compose and Material 3 for a smooth, attractive user experience.

- **Navigation**  
  Seamless navigation between screens with Jetpack Navigation Compose.

## Screenshots

*(Add screenshots here of the main screens if available)*

## Getting Started

### Prerequisites

- Android Studio Flamingo or newer
- JDK 11+
- Firebase project with Firestore enabled

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/mdgufranyt/QuickNotes.git
   ```

2. **Open in Android Studio**

3. **Firebase Configuration:**
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Register your app and download the `google-services.json` file
   - Place `google-services.json` in `app/` directory

4. **Build and run the app**

### Dependencies

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Firebase Firestore](https://firebase.google.com/docs/firestore)
- [Material 3](https://m3.material.io/)
- [AndroidX Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

## Project Structure

```
QuickNotes/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── java/com/quicknotes/
│       │   │   ├── MainActivity.kt
│       │   │   ├── models/
│       │   │   │   └── Notes.kt
│       │   │   ├── screens/
│       │   │   │   ├── NotesScreen.kt
│       │   │   │   └── InsertNotesScreen.kt
│       │   │   └── navigations/
│       │   │       └── NotesNavigations.kt
│       │   └── res/
│       └── test/
│           └── java/com/quicknotes/
│               └── ExampleUnitTest.kt
├── build.gradle.kts
└── settings.gradle.kts
```

## Usage

- Launch the app.
- On the home screen, tap the "+" button to create a new note.
- Notes are displayed in a list; tap to edit, or long press for more options (edit/delete).

## Code Highlights

- **NotesScreen.kt**: Displays list of notes, handles fetching from Firestore.
- **InsertNotesScreen.kt**: UI for adding and editing notes.
- **Notes.kt**: Data model for notes.
- **NotesNavigation.kt**: Manages app navigation.

## Contributing

Contributions are welcome! Please open issues and pull requests for bug fixes or new features.

## License

MIT License

---

*Created by [mdgufranyt](https://github.com/mdgufranyt)*
