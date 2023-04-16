# About The Project

[![My Skills](https://skillicons.dev/icons?i=kotlin,androidstudio&theme=light)](https://skillicons.dev)

This is a native Android application written in Kotlin language. The project contains a single user listing screen that displays the members of the team fetched from the provided JSON file "hipo.json". The screen is implemented using the MVVM design pattern.

## Data Models

The project uses the provided hipo.json file to create the data models. The data models were created using Kotlin's data class.

## MemberViewModel

The MemberViewModel class is responsible for managing the data and state of the "Member" objects in the app. The init block initializes the "membersMutable" and "filteredMembersMutable" lists with data assigned them from a JSON file called "hipo.json", which is stored in the app's assets folder. Third party "Gson" library used to parse the JSON data and convert it into a "Team" object, from which it extracts the list of "Member" objects.

The filterMembers function is called whenever the user types a search query in the search bar. It updates the "queryMutable" list with the new query and filters the "membersLive" list based on the query using the filter function. It then updates the "filteredMembersMutable" list with the filtered list.

Furthermore, the ViewModel class includes the "addMember" function to provide member addition with name "Koray Hamşioğlu" and position "intern". This function creates a new member (as me) and adds it to the current list of members. If the member is already in the list, it shows an error toast message to the user.

## Members Screen(MainActivity.kt)

The activity sets up the MemberViewModel object via ViewModelFactory class. Also, the activity creates MembersAdapter and binds it to the RecyclerView. The search function is implemented using a SearchView component and the MembersAdapter's filter function.

The "Add New Member" button triggers the addMember function of the MemberViewModel, which creates a new "Member" object and adds it to the list of members if it does not already exist. If the operation is successful, the adapter is notified to update the view with the new member.

The observeViewModel function observes the LiveData property of the MemberViewModel for changes and updates the adapter when a change is detected.

# Installation & Usage

1. Clone the repo 
  ```$ 
git clone https://github.com/KorayHamsioglu/HipoKotlinMVVM.git
```
2. Open the project in Android Studio
3. Build and run the project on an emulator or physical device

# Screenshots

<img width="220" alt="Ekran Resmi 2023-02-12 20 20 47" src="https://user-images.githubusercontent.com/87299676/232332288-b25a5d8a-30fa-43b6-8ac0-b7c0e43423d2.png"> 


