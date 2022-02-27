package com.kumparan.assesment.data.source

import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicePost {
    @GET("posts")
    fun getPostsAsync(): Deferred<List<Post>>

    @GET("comments")
    fun getPostCommentsAsync(@Query("postId") postId:Int): Deferred<List<Comment>>
}