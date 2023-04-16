package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson


class MemberViewModel(private val context: Context): ViewModel() {

    private val membersMutable= MutableLiveData<List<Member>>()
    val membersLive: LiveData<List<Member>> = membersMutable

    private val filteredMembersMutable=MutableLiveData<List<Member>>()
    val filteredMembersLive: LiveData<List<Member>> = filteredMembersMutable

    private val queryMutable = MutableLiveData<String>()

    val queryLive: LiveData<String> = queryMutable

    init {



        val jsonString = context.assets.open("hipo.json").bufferedReader().use { it.readText() }

        val team = Gson().fromJson(jsonString, Team::class.java)

        membersMutable.value = team.members

        filteredMembersMutable.value = team.members
    }

    fun filterMembers(query: String) {

        queryMutable.value = query

        val filtered = membersLive.value?.filter {
            it.name.contains(query, ignoreCase = true)
        }

        filteredMembersMutable.value = filtered
    }
    fun addMember() : Boolean {
        val member = Member(
            "Koray Hamşioğlu",
            26,
            "Istanbul",
            "KorayHamsioglu",
            Hipo(
                "Intern",
                0
            )
        )
        val currentList = membersLive.value?.toMutableList() ?: mutableListOf()
        if (currentList.any { it.name == "Koray Hamşioğlu" }) {
            Toast.makeText(context, "Member already exists", Toast.LENGTH_SHORT).show()
            return false
        } else {
            currentList.add(member)
            membersMutable.value = currentList
            filterMembers(queryLive.value ?: "")
            Toast.makeText(context, "Member added successfully", Toast.LENGTH_SHORT).show()
            return true
        }
    }

}

