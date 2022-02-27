package com.kumparan.assesment.model

import com.kumparan.assesment.data.model.Album
import com.kumparan.assesment.data.model.Photo
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AlbumTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var album: Album

    @Before
    fun setUp(){
        album = Album(1, 1, "")
    }

    @Test
    fun testPhoto() {
        val test =Album(1, 1, "",)
        assertEquals(album.id,test.id)
        assertEquals(album.userId,test.userId)
        assertEquals(album.title,test.title)
    }
}