package com.example.composechat

import android.app.Application

class TodosApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AppContainerImpl(this)
    }
}