package com.salientsys.salientandroidtest.main.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salientsys.salientandroidtest.R
import com.salientsys.salientandroidtest.data.entity.User
import timber.log.Timber

class UsersAdapter(private val onClick: (User) -> Unit) :
    ListAdapter<User, UsersAdapter.UserViewHolder>(UserDiffCallback) {

    /* ViewHolder for User, takes in the inflated view and the onClick behavior. */
    class UserViewHolder(itemView: View, val onClick: (User) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.item_user_name)
        private val username: TextView = itemView.findViewById(R.id.item_user_userName)
        private val email: TextView = itemView.findViewById(R.id.item_user_email)
        private var currentUser: User? = null

        init {
            itemView.setOnClickListener {
                currentUser?.let {
                    onClick(it)
                }
            }
        }

        /* Bind user name and image. */
        fun bind(user: User) {
            currentUser = user
            name.text = user.name
            username.text = user.username
            email.text = user.email
        }
    }

    /* Creates and inflates view and return UserViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view, onClick)
    }

    /* Gets current user and uses it to bind view. */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val user = getItem(position)
        Timber.d("on bind")
        holder.bind(user)
    }
}

object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}