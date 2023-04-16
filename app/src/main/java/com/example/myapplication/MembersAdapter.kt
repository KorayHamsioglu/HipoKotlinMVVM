package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MembersAdapter(private val viewModel: MemberViewModel) : RecyclerView.Adapter<MembersAdapter.MemberViewHolder>() {

    private var members: List<Member> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return MemberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(members[position])
    }

    override fun getItemCount(): Int {
        return members.size
    }

    fun setData(members: List<Member>) {
        this.members = members
        notifyDataSetChanged()
    }

    fun filter(text: String) {
        members = if (text.isEmpty()) {
            viewModel.membersLive.value.orEmpty()
        } else {
            viewModel.membersLive.value.orEmpty().filter { member ->
                member.name.contains(text, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(member: Member) {
            nameTextView.text = member.name
        }

        override fun onClick(view: View?) {


        }
    }
}
