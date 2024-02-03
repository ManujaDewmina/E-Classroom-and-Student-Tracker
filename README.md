# E-Classroom and Student Tracker

As a result of online education, it is very hard to keep a track on the students’ participation and it is very hard to communicate with them as a traditional classroom. So, we need a proper way to keep track on students and as well as give attention to students. And there must be a way to students can get every note which they got as printed materials in traditional classroom. E-Classroom and Student Tracker provides a better platform to both student and teachers whit ensuring above features.

## Features

- E-Classroom and student Tracker application consist of two user views, Student and Teacher. Both student and teacher need a user account to access the application. Both of user types must verify their emails before logged in. This application does not save the user account on the device and keeps the profile logged in because it detects user attendance using application login and using time.

- Through this application teachers can create online classroom and student can attend it using room ID. As well as students can also create online meetings to discuss with others. The user needs to grant permissions for the application to access the device’s camera and microphone. Both students and teachers can use voice chat and camera option and chat facility. Techers can change setting in a meeting. As well as it has facilities like whiteboard, screen sharing and live question pools. 

- When it comes to Study material section teachers can upload study materials and both students and teachers can search them using subject code or note code and download the pdf and use them. Students’ attendances are getting by the application automatically while they are using the app and when they log in to the application. So, students cannot just mark the attendance and leave from the online classroom. Both Students and teachers can see attendance history. Teachers can see attendance history of all students and students can see their own attendance history.

## System Architecture

<div align='center'>
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/8f7175fe-6d2f-4452-8c33-ffeece1403d1" width="600">
</div>

## Used Technologies
- Languages
<ul>
  <li>JAVA - For android mobile app development</li>
  <li>XML - For Mobile app interface designs</li>
</ul>
- SDK
<ul>
  <li>Jitsi Meet SDK : It allows secure, Simple and Scalable Video Conferences. A Zoom alternative, Jitsi Meet is an open-source (Apache) WebRTC JavaScript application that uses Jitsi Videobridge to provide high quality, secure and scalable video conferences.
</li>
</ul>
- Tools
<ul>
<li>Android Studio Bumblebee — 2021.1.1 Patch 2 used for android app development</li>
<li>GitHub - version control System</li>
</ul>
- Backend
<ul>
  <li>Authentication - Firebase Authentication</li>
  <li>Database - Firestore Database</li>
  <li>Storage - Firebase Storage</li>
  <li>Realtime Database - Firebase Realtime Database</li>
</ul>

## Interfaces

### 1. Sign in users and register new users
Users can log into their respective accounts if they have verified their accounts. Otherwise, users must register a new account by filling in their details. 
<div align='center'>
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/1cb8b5b8-8288-4a33-9e5a-9aee432cd8b1" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/d466c848-ee9e-434b-bf48-630194a1f295" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/5d570f00-e3ea-4ae4-aa3f-c5cbf8685bc2" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/502c7fc2-7274-4350-8b7f-8ac1d181825f" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/58caed62-f6e0-4348-ba5e-43b7c749913e" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/5a844d65-c8fd-416a-b56a-95209a6053c7" width="150">
</div>

### 2. Student and teacher home screens 
Both Students and teachers have different home screens. They have different features according to their user levels. 
<div align='center'>
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/c5ed1577-0b5e-464f-8c4c-66d2e913d6c4" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/9b2d5c3b-bfa5-47aa-9fe6-ac8592540022" width="150">
</div>

### 3. Online meeting
Both Students and teachers can create online meetings and generate room IDs. Once the meeting is created, others can join using that room ID. 
<div align='center'>
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/98be5364-5c03-4b61-afc3-20713b8eb5d1" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/78189639-bae3-4422-89d3-94c6edaff5c8" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/be038f48-0931-4c5c-a921-fb6119ba4fae" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/63fbe313-2898-4dcd-8fa3-fb2d35fd0b9f" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/09ccf503-40c8-443a-afaf-4f46c5e6d57f" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/45c3a44f-a542-4409-afac-2016b5d6400b" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/a850d1cc-44ba-4870-b6af-654750ff0aa0" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/9db96c9a-6ede-42e4-9aca-4986bb3b077c" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/a090fe2c-b71f-43a4-b344-30335ff29ba8" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/a6e88956-0cff-4b73-a9bc-ff7737e9e7b6" width="150">
</div>

### 4. Manage study materials
Teachers can upload PDFs and see uploaded notes. But students can only see uploaded PDFs. Both can search for PDFs using Subject Code. By clicking on it, users can download the PDF.
<div align='center'>
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/48c9de00-139d-4e6a-89a7-ce75056c46ce" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/97649854-7bb3-4281-832b-536727b12b31" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/84108f86-bf2f-479d-aa07-029ece98d2a7" width="150">
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/f8b08121-0d3b-4a4a-bf8c-e3678913d5fb" width="150">
</div>

### 5. Attendance tracker
Both Students and teachers can see attendance. Teachers can see every student's attendance, and students can see their own attendance.
<div align='center'>
  <img src="https://github.com/ManujaDewmina/E-Classroom-and-Student-Tracker/assets/92631934/c20f8099-ea41-4e60-9425-0caf5f8f8ca3" width="150">
</div>
