package com.kumparan.assesment.ui.viewmodel

import android.app.Application
import com.kumparan.assesment.ui.base.BaseViewModel
import com.kumparan.assesment.ui.navigator.PhotoDetailNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(

    app: Application
) : BaseViewModel<PhotoDetailNavigator>(app)