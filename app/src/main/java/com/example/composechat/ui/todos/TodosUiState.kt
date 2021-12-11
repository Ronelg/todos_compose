package com.example.composechat.ui.todos

import androidx.compose.runtime.mutableStateListOf
import com.example.composechat.data.Todo
import java.util.*

class TodosUiState(
    initialTodos: List<Todo> = emptyList()
) {
    private val _todos: MutableList<Todo> =
        mutableStateListOf(*initialTodos.toTypedArray())
    val todos: List<Todo> = _todos

    fun toggle(id: String) {
        val newTodos = mutableListOf<Todo>()
        todos.forEach { todo ->
            if (todo.id == id) {
                newTodos.add(todo.copy(isCompleted = !todo.isCompleted))
            } else {
                newTodos.add(todo)
            }
        }
        _todos.clear()
        _todos.addAll(newTodos)
    }

    fun addTodo() {
        _todos.add(
            Todo(
                id = UUID.randomUUID().toString(),
                title = "Mom birthday",
                description = "Love flowers",
                timestamp = "8:05 PM",
                isCompleted = false,
            )
        )
    }
}

