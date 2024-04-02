package com.example.utspam

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.utspam.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val list = ArrayList<User>()
    inner class ViewHolder (
        private val binding: ItemUserBinding

    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            binding.textViewName.text = user.first_name + user.last_name
            binding.textViewEmail.text = user.email
            Glide.with(itemView)
                .load(user.avatar)
                .centerCrop()
                .into(binding.imageViewAvatar)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size
    fun updateData(user: List<User>) {
        list.clear()
        list.addAll(user)
        notifyDataSetChanged()
    }
}

