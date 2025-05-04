# 💊 Drug Search and Tracker - Android Assessment Project

This is a take-home Android assessment project for a house call company. The application allows users to search for drugs via the RxNorm API, view detailed drug information, and track their personal medication usage. It integrates secure authentication using Firebase and supports local data persistence using SQLite.

---

## 🚀 Features Covered

### ✅ Authentication
- Firebase Authentication (Email/Password)
- Implemented using Jetpack Compose UI screens
- Navigation handled via **Jetpack Compose Navigation**
- Single-activity structure with composable-based screen flow

### ✅ Drug Search
- Search using RxNorm public API:
    - Endpoint: `https://rxnav.nlm.nih.gov/REST/drugs.json?name=<DRUG_NAME>&expand=psn`
    - Filters for `tty = SBD` and displays top 10 results
- Shows:
    - `rxcui` ID
    - Drug name

### ✅ User Medication Management
- User can add up to **3 drugs**
- Drugs saved in **SQLite - Room database**
- Combined list of:
    - Drugs fetched from API
    - drugs stored locally
- Swipe to delete functionality for removing drugs from the list

### ✅ Data Persistence
- SQLite(Room) used for storing user-added drugs

---

## 🧪 Requirements Summary

| Requirement                          | Status        |
|--------------------------------------|---------------|
| Firebase Authentication (Email/Pass) | ✅ Implemented |
| RxNorm API integration (getDrugs)    | ✅ Implemented |
| Drug detail screen                   | ✅ Implemented |
| SQLite local storage                 | ✅ Implemented |
| Add new drug (limit 3)               | ✅ Implemented |
| Swipe to delete drug                 | ✅ Implemented |
| Show all user drugs after login      | ✅ Implemented |
| MVVM or MVC architecture             | ✅ MVVM        |
| JAVA SDK Version                     | ✅ 21          |
| Android 10+ target                   | ✅ API 29+     |

---

## 🛠 Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM
- **UI**: Jetpack Compose
- **Networking**: Retrofit, OkHttp
- **Authentication**: Firebase Auth (Email/Password)
- **Database**: SQLite via Room ORM
- **Dependency Injection**: Hilt
- **Other**: Flows, ViewModel, Coroutines

---

## ⚙️ Setup & Installation

1. Clone the repository:
   ```bash
   https://github.com/Abdulqa/House-Call-Assessment.git
