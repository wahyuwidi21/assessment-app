package com.kumparan.assesment.data.repository

import com.kumparan.assesment.data.model.*
import com.kumparan.assesment.data.source.ApiServiceUser
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiServiceUser: ApiServiceUser
) {

    suspend fun getUserRemote(userId: Int): User {
        return apiServiceUser.getUserAsync(userId).await()
    }

    suspend fun getUserAlbums(userId: Int): List<Album> {
        return apiServiceUser.getUserAlbumsAsync(userId).await()
    }

    suspend fun getUserPhotos(albumId: Int): List<Photo> {
        return apiServiceUser.getAlbumPhotosAsync(albumId).await()
    }

    suspend fun getAlbumsWithPhotos(userId: Int):List<HashMap<Album,List<Photo>>>{
        val albums = getUserAlbums(userId)
        val albumsWithPhotos = mutableListOf<HashMap<Album,List<Photo>>>()

        albums.forEach {
            val photos = getUserPhotos(it.id)
            albumsWithPhotos.add(hashMapOf(Pair(it,photos)))
        }
        return albumsWithPhotos
    }

}