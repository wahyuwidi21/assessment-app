package com.kumparan.assesment.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kumparan.assesment.CoroutinesTestRule
import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.repository.PostRepository
import com.kumparan.assesment.ui.viewmodel.DetailPostViewModel
import com.kumparan.assesment.ui.viewmodel.PhotoDetailViewModel
import com.kumparan.assesment.utils.NetworkHelper
import com.kumparan.assesment.utils.Resource
import junit.framework.Assert.assertEquals
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
class PhotoViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var viewModel: PhotoDetailViewModel
    @Before
    fun setUp() {
        viewModel =
            PhotoDetailViewModel(
                 application
            )
    }

    @Test
    fun `test empty view model`() {
      val vm= viewModel
        assertEquals(viewModel,vm)
    }
}