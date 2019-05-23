package com.example.testmvp.post

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testmvp.R
import com.example.testmvp.entity.Post
import kotlinx.android.synthetic.main.item_post.view.*

class RecyclerPostAdapter(var posts: ArrayList<Post>) : RecyclerView.Adapter<RecyclerPostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_post, p0, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.initPosts(posts[p1])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initPosts(post: Post) {
            itemView.tvPostTitle.text = post.title

        }
    }
}