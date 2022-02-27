package com.kumparan.assesment.model

import android.os.Parcel
import com.kumparan.assesment.MockParcel
import com.kumparan.assesment.data.model.PostFinal
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PostFinalTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var parcel: Parcel

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var postFinal: PostFinal

    @Before
    fun setUp(){
        postFinal = PostFinal(1, "test nama", 1, "company", "title", "body")

    }
    @Test
    fun testTestClassParcelable() {
        val test = PostFinal(1, "test nama", 1, "company", "title", "body")

        // Obtain a Parcel object and write the parcelable object to it:
        parcel = MockParcel.obtain()
        test.writeToParcel(parcel, 0)
        test.describeContents()
        // After you're done with writing, you need to reset the parcel for reading:
        parcel.setDataPosition(0)

        // Reconstruct object from parcel and asserts:
        val createdFromParcel: PostFinal = PostFinal.CREATOR.createFromParcel(parcel)
        PostFinal.newArray(1)
        assertEquals(test, createdFromParcel)
        assertEquals(test.describeContents(),createdFromParcel.describeContents())
    }

    @Test
    fun testPostFinal() {
        val test = PostFinal(1, "test nama", 1, "company", "title", "body")
        assertEquals(postFinal.body, test.body)
        assertEquals(postFinal.title, test.title)
        assertEquals(postFinal.companyName, test.companyName)
        assertEquals(postFinal.postId, test.postId)
        assertEquals(postFinal.userName, test.userName)
        assertEquals(postFinal.body, test.body)
        assertEquals(postFinal.userId, test.userId)
    }
}