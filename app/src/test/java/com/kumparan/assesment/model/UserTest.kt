package com.kumparan.assesment.model

import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.model.Photo
import com.kumparan.assesment.data.model.User
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UserTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    lateinit var user: User

    @Before
    fun setUp(){
        user =  User(
            1,
            "test nama",
            "username test",
            "email test",
            User.Address(
                "jalan test", "suite test", "city test", "zipcode test",
                User.Address.Geo("lat", "lng")
            ), "phone", "website", User.Company("company", "cp", "bs")
        )
    }

    @Test
    fun testUser() {
        val test =User(
            1,
            "test nama",
            "username test",
            "email test",
            User.Address(
                "jalan test", "suite test", "city test", "zipcode test",
                User.Address.Geo("lat", "lng")
            ), "phone", "website", User.Company("company", "cp", "bs")
        )
        assertEquals(user.id,test.id)
        assertEquals(user.email,test.email)
        assertEquals(user.address,test.address)
        assertEquals(user.company,test.company)
        assertEquals(user.name,test.name)
        assertEquals(user.phone,test.phone)
        assertEquals(user.username,test.username)
        assertEquals(user.website,test.website)
    }

    @Test
    fun testUserAddress() {
        val test =User(
            1,
            "test nama",
            "username test",
            "email test",
            User.Address(
                "jalan test", "suite test", "city test", "zipcode test",
                User.Address.Geo("lat", "lng")
            ), "phone", "website", User.Company("company", "cp", "bs")
        )
        assertEquals(user.address.city,test.address.city)
        assertEquals(user.address.geo,test.address.geo)
        assertEquals(user.address.street,test.address.street)
        assertEquals(user.address.suite,test.address.suite)
        assertEquals(user.address.zipcode,test.address.zipcode)
        assertEquals(user.address.geo.lat,test.address.geo.lat)
        assertEquals(user.address.geo.lng,test.address.geo.lng )
    }

    @Test
    fun testUserCompany() {
        val test =User(
            1,
            "test nama",
            "username test",
            "email test",
            User.Address(
                "jalan test", "suite test", "city test", "zipcode test",
                User.Address.Geo("lat", "lng")
            ), "phone", "website", User.Company("company", "cp", "bs")
        )
        assertEquals(user.company.name,test.company.name)
        assertEquals(user.company.bs,test.company.bs)
        assertEquals(user.company.catchPhrase,test.company.catchPhrase)
    }
}