package com.kumparan.assesment.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kumparan.assesment.CoroutinesTestRule
import com.kumparan.assesment.data.model.Album
import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.model.Photo
import com.kumparan.assesment.data.model.User
import com.kumparan.assesment.data.repository.PostRepository
import com.kumparan.assesment.data.repository.UserRepository
import com.kumparan.assesment.ui.viewmodel.DetailPostViewModel
import com.kumparan.assesment.ui.viewmodel.UserDetailViewModel
import com.kumparan.assesment.utils.NetworkHelper
import com.kumparan.assesment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
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
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class UserDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var application: Application

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var viewModel: UserDetailViewModel

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var networkHelper: NetworkHelper

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var responseUser: User

    @Mock
    private lateinit var responseAlbumWithPhotos: List<HashMap<Album,List<Photo>>>

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var userObserver: Observer<Resource<User>>

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var albumWithPhotoObserver: Observer<Resource<List<HashMap<Album,List<Photo>>>>>
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            UserDetailViewModel(
                userRepository,
                networkHelper, application
            )
    }

    @Test
    fun `given success response when get user`() = coroutinesTestRule.runBlocking {
        Mockito.`when`(userRepository.getUserRemote(1))
            .thenReturn(responseUser)

        viewModel.user.observeForever(userObserver)
        viewModel.getUser(1)

        launch(Dispatchers.Main) {
            Mockito.verify(userObserver).onChanged(Resource.loading(null))
            Mockito.verify(userObserver).onChanged(Resource.success(responseUser))
        }
        viewModel.user.removeObserver(userObserver)
    }

    @Test
    fun `given error response when get comments`() {
        runBlocking {
            val exception = RuntimeException()
            Mockito.`when`(userRepository.getUserRemote(1))
                .thenThrow(exception)

            viewModel.user.observeForever(userObserver)
            viewModel.getUser(1)

            launch(Dispatchers.Main) {
                Mockito.verify(userObserver).onChanged(Resource.loading(null))
                Mockito.verify(userObserver).onChanged(
                    Resource.error(
                        exception.toString(), null,
                        Throwable("", null)
                    )
                )
            }
            viewModel.user.removeObserver(userObserver)
        }
    }

    @Test
    fun `given success response when get album with photos`() = coroutinesTestRule.runBlocking {
        Mockito.`when`(userRepository.getAlbumsWithPhotos(1))
            .thenReturn(responseAlbumWithPhotos)

        viewModel.albumWithPhoto.observeForever(albumWithPhotoObserver)
        viewModel.getAlbumsWithPhotos(1)

        launch(Dispatchers.Main) {
            Mockito.verify(albumWithPhotoObserver).onChanged(Resource.loading(null))
            Mockito.verify(albumWithPhotoObserver).onChanged(Resource.success(responseAlbumWithPhotos))
        }
        viewModel.albumWithPhoto.removeObserver(albumWithPhotoObserver)
    }

    @Test
    fun `given error response when get album with photos`() {
        runBlocking {
            val exception = RuntimeException()
            Mockito.`when`(userRepository.getAlbumsWithPhotos(1))
                .thenThrow(exception)

            viewModel.albumWithPhoto.observeForever(albumWithPhotoObserver)
            viewModel.getAlbumsWithPhotos(1)

            launch(Dispatchers.Main) {
                Mockito.verify(albumWithPhotoObserver).onChanged(Resource.loading(null))
                Mockito.verify(albumWithPhotoObserver).onChanged(
                    Resource.error(
                        exception.toString(), null,
                        Throwable("", null)
                    )
                )
            }
            viewModel.albumWithPhoto.removeObserver(albumWithPhotoObserver)
        }
    }

    @After
    fun tearDown() {
    }
}