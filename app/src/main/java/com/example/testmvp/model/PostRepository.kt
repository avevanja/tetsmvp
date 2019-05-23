package com.example.testmvp.model

import android.annotation.SuppressLint
import com.example.testmvp.TestMvpApplication
import com.example.testmvp.entity.Post
import com.example.testmvp.model.network.RetrofitBuilder
import com.example.testmvp.post.mvp.GetPostCallBack
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostRepository {

    val dataBaseDao = TestMvpApplication.instance.getDb().dataBaseDao()
    val api = RetrofitBuilder.getApi()
    val disposable = CompositeDisposable()

    fun getPost(callback: GetPostCallBack) {

        val callbackPost = object : Callback<ArrayList<Post>> {
            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    if (!posts.isNullOrEmpty()) {
                        callback.getPostsSuccess(posts)
                        Completable.fromAction {
                            dataBaseDao.insert(posts)
                        }.observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe {

                            }
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                callback.errorGetPosts(t.localizedMessage)
            }
        }
        disposable.add(dataBaseDao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isEmpty()) {
                    api.getPosts().enqueue(callbackPost)
                } else {
                    callback.getPostsSuccess(ArrayList(it))
                }
            })

    }
}