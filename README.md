# Todo_List_Application
(App Developed with name Roche_Assessment)
## Jetpack Compose Todo List Application with Firebase Authentication and Room Database
This is a todo list application built using Jetpack Compose, Firebase Authentication, and Room Database. The application allows users to create tasks and manage them, and the tasks are stored in a local database using Room Database. Users can sign in using Firebase Authentication and their tasks persist even if the application is force-closed.

## Key Features
- Room Database for local data storage.
- Firebase Authentication for user authentication and management.
- Jetpack Compose UI for creating a modern and efficient user interface.
- Task creation and management functionality, including adding, and deleting tasks.
- Data persistence, which allows users to view their tasks even if the application is force-closed or restarted.
- Unless the user explicitly clicks on the sign-out button, the application will not sign out the user.

## Video
[![Watch the video](https://img.youtube.com/vi/C76h-kGapkQ/maxresdefault.jpg)](https://youtu.be/C76h-kGapkQ)
https://www.youtube.com

## Code Structure
The application is divided into the following components:

- MainActivity: The main activity of the application that launches the Welcome Screen if the user didn't sign out in the previous session, or the Login Activity if the user is not signed in.
- Login Activity: Contains Firebase Authentication. When the user clicks on the sign-in button, they can sign in by google sign in or register using by manually entering their email and password or sign in by manually entering those credentials.
- Welcome Screen: Displays the task list and allows the user to create, or delete tasks.
- TaskItem: An entity class that defines the structure of the TaskItemTable.
- TaskDAO: A DAO (Data Access Object) interface that provides methods for performing CRUD (Create, Read, Update, Delete) operations on the TaskItem table in the Room Database. I have also written two custom methods with custom queries getAll() which returns LiveData<List<TaskItem>> and updateTaskItemChekced() which updates the checkbox state of each task in the room database whenever there is a change.
- TaskDatabase: A Room Database that stores the TaskItem objects.
