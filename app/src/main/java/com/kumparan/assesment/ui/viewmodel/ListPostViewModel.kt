package com.kumparan.assesment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kumparan.assesment.data.model.PostFinal
import com.kumparan.assesment.data.repository.PostRepository
import com.kumparan.assesment.ui.base.BaseViewModel
import com.kumparan.assesment.ui.navigator.ListPostNavigator
import com.kumparan.assesment.utils.NetworkHelper
import com.kumparan.assesment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListPostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val networkHelper: NetworkHelper,
    app: Application
) : BaseViewModel<ListPostNavigator>(app) {


    private val _postData = MutableLiveData<Resource<List<PostFinal>>>()
    val postData: MutableLiveData<Resource<List<PostFinal>>>
        get() = _postData

    internal fun getPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _postData.postValue(Resource.loading(null))
                try {
                    _postData.postValue(
                        Resource.success(
                            postRepository.getListPostData()
                        )
                    )
                } catch (e: Exception) {
                    _postData.postValue(Resource.error(e.toString(), null, Throwable("", null)))
                }
            }
        }
    }
}