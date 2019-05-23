package com.example.testmvp.post

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.Toast
import com.example.testmvp.R
import com.example.testmvp.entity.Post
import com.example.testmvp.post.mvp.PostPresenter
import com.example.testmvp.post.mvp.PostView
import kotlinx.android.synthetic.main.activity_main.*

class PostActivity : AppCompatActivity(), PostView {

    private lateinit var presenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        initRecycler()
        presenter.getPosts()
    }

    private fun initPresenter() {
        presenter = PostPresenter(this)
    }

    private fun initRecycler() {
        rvPosts.layoutManager = LinearLayoutManager(this)
    }

    override fun showProgress(isShow: Boolean) {
        progressBar.visibility = if (isShow) VISIBLE else GONE
    }

    override fun setPosts(posts: ArrayList<Post>) {
        rvPosts.adapter = RecyclerPostAdapter(posts)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
