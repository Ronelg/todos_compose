package com.example.composechat.data

import com.example.composechat.util.addOrRemove
import kotlinx.coroutines.flow.*

class TodosRepository {

    companion object {
        private val todosRepository: TodosRepository = TodosRepository()

        fun getInstance() = todosRepository
    }

    private val completed = MutableStateFlow<Set<String>>(setOf())

    private val todos = MutableSharedFlow<List<Todo>>(replay = 1).apply {
        tryEmit(initialTodos)
    }

    fun getTodos() = todos.asSharedFlow()

    fun addTodo(todo: Todo) {
        val list = todos.replayCache[0].toMutableList()
        list.add(todo)
        todos.tryEmit(list)
    }

    fun observeCompleted(): Flow<Set<String>> = completed

    fun toggleComplete(todoId: String) {
        val set = completed.value.toMutableSet()
        set.addOrRemove(todoId)
        completed.value = set.toSet()
    }
}