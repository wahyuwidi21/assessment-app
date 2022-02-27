package com.kumparan.assesment.model

import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.model.Post
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PostTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var post: Post

    @Before
    fun setUp(){
        post = Post(1, 1, "", "")
    }

    @Test
    fun testPost() {
        val test =Post(1, 1, "", "")
        assertEquals(post.id,test.id)
        assertEquals(post.body,test.body)
        assertEquals(post.userId,test.userId)
        assertEquals(post.title,test.title)
    }
}