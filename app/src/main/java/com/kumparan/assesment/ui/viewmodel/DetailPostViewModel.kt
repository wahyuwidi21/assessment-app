package com.kumparan.assesment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kumparan.assesment.data.model.Comment
import com.kumparan.assesment.data.repository.PostRepository
import com.kumparan.assesment.ui.base.BaseViewModel
import com.kumparan.assesment.ui.navigator.DetailPostNavigator
import com.kumparan.assesment.utils.NetworkHelper
import com.kumparan.assesment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val networkHelper: NetworkHelper,
    app: Application
) : BaseViewModel<DetailPostNavigator>(app) {


    private val _comments = MutableLiveData<Resource<List<Comment>>>()
    val comments: MutableLiveData<Resource<List<Comment>>>
        get() = _comments

    internal fun getComments(postId:Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _comments.postValue(Resource.loading(null))
                try {
                    _comments.postValue(
                        Resource.success(
                            postRepository.getCommentsRemote(postId)
                        )
                    )
                } catch (e: Exception) {
                    _comments.postValue(Resource.error(e.toString(), null, Throwable("", null)))
                }
            }
        }
    }
}