package com.example.testmvp.post.mvp

import com.example.testmvp.entity.Post
import com.example.testmvp.model.PostRepository

class PostPresenter(val view: PostView) {

    lateinit var repository: PostRepository

    init {
        initRepository()
    }

    private fun initRepository() {
        repository = PostRepository()
    }

    fun getPosts() {
        view.showProgress(true)
        repository.getPost(object : GetPostCallBack {
            override fun getPostsSuccess(posts: ArrayList<Post>) {
                view.setPosts(posts)
                view.showProgress(false)
            }

            override fun errorGetPosts(message: String) {
                view.showError(message)
                view.showProgress(false)
            }
        })
    }

}

interface GetPostCallBack {
    fun getPostsSuccess(posts: ArrayList<Post>)
    fun errorGetPosts(message: String)
}