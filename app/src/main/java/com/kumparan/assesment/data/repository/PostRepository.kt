package com.kumparan.assesment.data.repository

import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.model.Post
import com.kumparan.assesment.data.model.PostFinal
import com.kumparan.assesment.data.model.User
import com.kumparan.assesment.data.source.ApiServicePost
import com.kumparan.assesment.data.source.ApiServiceUser
import kotlinx.coroutines.flow.*
import javax.inject.Inject

open class PostRepository @Inject constructor(
    private val apiServicePost: ApiServicePost,
    private val apiServiceUser: ApiServiceUser
) {

    private suspend fun getPostsRemote(): List<Post> {
        return apiServicePost.getPostsAsync().await()
    }

    private suspend fun getUserRemote(): List<User> {
        return apiServiceUser.getUsersAsync().await()
    }

    suspend fun getCommentsRemote(postId:Int): List<Comment> {
        return apiServicePost.getPostCommentsAsync(postId).await()
    }

    suspend fun getListPostData(): List<PostFinal> {
        val post = getPostsRemote()
        val user = getUserRemote()

        return post.asFlow().map { postElement ->
            val userObject = user.find { item -> item.id == postElement.userId }
            val name = userObject?.name ?: "-"
            val companyName = userObject?.company?.name ?: "-"
            val id = userObject?.id ?: 0
            PostFinal(postElement.id,name, id, companyName,postElement.title, postElement.body)
        }.toList()
    }
}