package com.kumparan.assesment

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssesmentApp: Application() {

    init {
        instance = this
    }


    companion object {
        private var instance: AssesmentApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }


}