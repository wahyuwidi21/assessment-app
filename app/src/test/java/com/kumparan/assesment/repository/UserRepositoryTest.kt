package com.kumparan.assesment.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kumparan.assesment.CoroutinesTestRule
import com.kumparan.assesment.data.model.Album
import com.kumparan.assesment.data.model.Photo
import com.kumparan.assesment.data.model.User
import com.kumparan.assesment.data.repository.UserRepository
import com.kumparan.assesment.data.source.ApiServiceUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class UserRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var apiServiceUser: ApiServiceUser

    @Mock
    private lateinit var listAlbumResponse: List<Album>

    @Mock
    private lateinit var userResponse: User

    @Mock
    private lateinit var listPhotoResponse: List<Photo>

    @Mock
    private lateinit var listAlbumWithPhotos: List<HashMap<Album, List<Photo>>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        apiServiceUser = Mockito.mock(ApiServiceUser::class.java, Mockito.RETURNS_DEEP_STUBS)
        listAlbumWithPhotos =
            listOf(hashMapOf(Pair(Album(1, 1, ""), listOf(Photo(1, 1, "", "", "")))))
        listAlbumResponse = listOf(Album(1, 1, ""))
        listPhotoResponse = listOf(Photo(1, 1, "", "", ""))
        userResponse =
            User(
                1,
                "test nama",
                "username test",
                "email test",
                User.Address(
                    "jalan test", "suite test", "city test", "zipcode test",
                    User.Address.Geo("lat", "lng")
                ), "phone", "website", User.Company("company", "cp", "bs")
            )

        userRepository =
            UserRepository(apiServiceUser)

    }

    //
    @Test
    fun `given success response when get user remote`() {
        runBlocking {
            Mockito.`when`(apiServiceUser.getUserAsync(1).await())
                .thenReturn(userResponse)

            val data = userRepository.getUserRemote(1)

            assertEquals(userResponse, data)
        }
    }

    @Test
    fun `given success response when get album remote`() {
        runBlocking {
            Mockito.`when`(apiServiceUser.getUserAlbumsAsync(1).await())
                .thenReturn(listAlbumResponse)

            val data = userRepository.getUserAlbums(1)

            assertEquals(listAlbumResponse, data)
        }
    }

    @Test
    fun `given success response when get photos remote`() {
        runBlocking {
            Mockito.`when`(apiServiceUser.getAlbumPhotosAsync(1).await())
                .thenReturn(listPhotoResponse)

            val data = userRepository.getUserPhotos(1)

            assertEquals(listPhotoResponse, data)
        }
    }

    @Test
    fun `given success response when get album with photos remote`() {
        runBlocking {
            Mockito.`when`(apiServiceUser.getUserAlbumsAsync(1).await())
                .thenReturn(listAlbumResponse)
//
            Mockito.`when`(apiServiceUser.getAlbumPhotosAsync(1).await())
                .thenReturn(listPhotoResponse)

            val fixData = userRepository.getAlbumsWithPhotos(1) as List<HashMap<Album, List<Photo>>>

            assertEquals(listAlbumWithPhotos, fixData)
        }
    }
}