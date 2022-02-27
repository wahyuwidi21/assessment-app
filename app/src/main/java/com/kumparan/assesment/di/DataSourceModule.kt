package com.kumparan.assesment.di

import com.kumparan.assesment.di.NetworkModule.NetworkModule.provideClientPost
import com.kumparan.assesment.di.NetworkModule.NetworkModule.provideMoshiConverter
import com.kumparan.assesment.di.NetworkModule.NetworkModule.provideOkHttpClient
import com.kumparan.assesment.data.source.ApiServicePost
import com.kumparan.assesment.data.source.ApiServiceUser
import com.kumparan.assesment.di.GlobalVariable.getApiBaseUrl
import com.kumparan.assesment.di.NetworkModule.NetworkModule.provideClientUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRemotePost() : ApiServicePost {
        return provideClientPost(provideOkHttpClient(), getApiBaseUrl() ,provideMoshiConverter())
    }

    @Provides
    @ActivityRetainedScoped
    fun provideClientUser() : ApiServiceUser {
        return provideClientUser(provideOkHttpClient(), getApiBaseUrl() ,provideMoshiConverter())
    }

}