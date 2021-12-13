package com.example.composechat

import android.content.Context
import com.example.composechat.data.TodosRepository

interface AppContainer {
    val todosRepository: TodosRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val todosRepository: TodosRepository by lazy {
        TodosRepository()
    }
}
