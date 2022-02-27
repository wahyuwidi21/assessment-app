package com.kumparan.assesment.data.source

import com.kumparan.assesment.data.model.Album
import com.kumparan.assesment.data.model.Photo
import com.kumparan.assesment.data.model.Post
import com.kumparan.assesment.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceUser {
    @GET("users")
    fun getUsersAsync(): Deferred<List<User>>

    @GET("users/{id}")
    fun getUserAsync(@Path("id") userId: Int): Deferred<User>

    @GET("albums")
    fun getUserAlbumsAsync(@Query("userId") userId: Int): Deferred<List<Album>>

    @GET("photos")
    fun getAlbumPhotosAsync(@Query("albumId") albumId: Int): Deferred<List<Photo>>
}