package com.kumparan.assesment.model

import android.os.Parcel
import com.kumparan.assesment.MockParcel
import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.model.PostFinal
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CommentTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var comment: Comment

    @Before
    fun setUp(){
        comment = Comment(1, 1, "", "", "")
    }

    @Test
    fun testComment() {
        val test =Comment(1, 1, "", "", "")
        assertEquals(comment.id,test.id)
        assertEquals(comment.body,test.body)
        assertEquals(comment.email,test.email)
        assertEquals(comment.name,test.name)
        assertEquals(comment.postId,test.postId)
    }
}