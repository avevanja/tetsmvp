package com.example.testmvp.post.mvp

import com.example.testmvp.entity.Post

interface PostView {
    fun setPosts(posts: ArrayList<Post>)
    fun showError(message: String)
    fun showProgress(isShow: Boolean)
}