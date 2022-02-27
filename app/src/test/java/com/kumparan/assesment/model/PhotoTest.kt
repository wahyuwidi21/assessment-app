package com.kumparan.assesment.model

import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.model.Photo
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PhotoTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var photo: Photo

    @Before
    fun setUp(){
        photo = Photo(1, 1, "", "", "")
    }

    @Test
    fun testPhoto() {
        val test =Photo(1, 1, "", "", "")
        assertEquals(photo.id,test.id)
        assertEquals(photo.albumId,test.albumId)
        assertEquals(photo.thumbnailUrl,test.thumbnailUrl)
        assertEquals(photo.title,test.title)
        assertEquals(photo.url,test.url)
    }
}