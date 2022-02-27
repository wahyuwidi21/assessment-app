package com.kumparan.assesment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kumparan.assesment.data.model.Album
import com.kumparan.assesment.data.model.Photo
import com.kumparan.assesment.data.model.User
import com.kumparan.assesment.data.repository.UserRepository
import com.kumparan.assesment.ui.base.BaseViewModel
import com.kumparan.assesment.ui.navigator.UserDetailNavigator
import com.kumparan.assesment.utils.NetworkHelper
import com.kumparan.assesment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper,
    app: Application
) : BaseViewModel<UserDetailNavigator>(app) {


    private val _user = MutableLiveData<Resource<User>>()
    val user: MutableLiveData<Resource<User>>
        get() = _user

    private val _albumWithPhoto = MutableLiveData<Resource<List<HashMap<Album, List<Photo>>>>>()
    val albumWithPhoto: MutableLiveData<Resource<List<HashMap<Album, List<Photo>>>>>
        get() = _albumWithPhoto


    internal fun getUser(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _user.postValue(Resource.loading(null))
                try {
                    _user.postValue(
                        Resource.success(
                            userRepository.getUserRemote(userId)
                        )
                    )
                } catch (e: Exception) {
                    _user.postValue(Resource.error(e.toString(), null, Throwable("", null)))
                }
            }
        }
    }

    internal fun getAlbumsWithPhotos(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _albumWithPhoto.postValue(Resource.loading(null))
                try {

                    _albumWithPhoto.postValue(
                        Resource.success(
                            userRepository.getAlbumsWithPhotos(
                                userId
                            )
                        )
                    )

                } catch (e: Exception) {
                    _albumWithPhoto.postValue(
                        Resource.error(
                            e.toString(),
                            null,
                            Throwable("", null)
                        )
                    )
                }
            }
        }
    }
}