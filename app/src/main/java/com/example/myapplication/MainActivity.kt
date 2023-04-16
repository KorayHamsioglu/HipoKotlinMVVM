package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MemberViewModel
    private lateinit var membersAdapter: MembersAdapter

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MemberViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(MemberViewModel::class.java)

        membersAdapter = MembersAdapter(viewModel)

        val layoutManager = LinearLayoutManager(this)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = membersAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!TextUtils.isEmpty(newText)) {
                    membersAdapter.filter(newText!!)
                }else {
                    membersAdapter.filter("")
            }
                return true
            }
        })

        binding.buttonAddNew.setOnClickListener {
            val isTrue: Boolean=viewModel.addMember()
            if (isTrue){
                membersAdapter.notifyItemInserted(viewModel.membersLive.value.orEmpty().size - 1)

            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.membersLive.observe(this, { members ->
            membersAdapter.setData(members)
        })
    }

}
