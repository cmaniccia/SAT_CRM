package com.salientsys.salientandroidtest.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salientsys.salientandroidtest.R
import com.salientsys.salientandroidtest.data.entity.Post
import timber.log.Timber


class PostAdapter(private val onClick: (Post) -> Unit) :
    ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback) {

    /* ViewHolder for Post, takes in the inflated view and the onClick behavior. */
    class PostViewHolder(itemView: View, val onClick: (Post) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val body: TextView = itemView.findViewById(R.id.item_post_body)
        private val id: TextView = itemView.findViewById(R.id.item_post_id)
        private val title: TextView = itemView.findViewById(R.id.item_post_title)
        private val userId: TextView = itemView.findViewById(R.id.item_post_userId)
        private var currentPost: Post? = null


        init {
            itemView.setOnClickListener {
                currentPost?.let {
                    onClick(it)
                }
            }
        }

        /* Bind post name and image. */
        fun bind(post: Post) {
            currentPost = post
            body.text = post.body
            id.text = post.id.toString()
            title.text = post.title
            userId.text = post.userId.toString()

        }
    }

    /* Creates and inflates view and return PostViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view, onClick)
    }

    /* Gets current post and uses it to bind view. */
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val post = getItem(position)
        Timber.d("on bind")
        holder.bind(post)
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
}