package com.example.composechat.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composechat.data.Todo
import com.example.composechat.data.TodosRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class TodosViewModel : ViewModel() {

    private val todosRepository = TodosRepository.getInstance()

    private val viewModelState = MutableStateFlow(TodosVieModelState())

    private val showOnlyCompleted = MutableStateFlow(false)

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toTodosUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toTodosUiState()
        )

    init {
        // Observe for completed todos changes in the repo layer
        viewModelScope.launch {
            combine(
                todosRepository.getTodos(),
                todosRepository.observeCompleted(),
                showOnlyCompleted
            ) { todos, completed, filter ->
                val list = if(filter) todos.filter { completed.contains(it.id) }  else todos
                viewModelState.update { it.copy(completed = completed, todos = list) }
            }.collect {  }
        }
    }

    fun toggleTodo(todoId: String) {
        viewModelScope.launch {
            todosRepository.toggleComplete(todoId)
        }
    }

    fun addTodo() {
        viewModelScope.launch {
            todosRepository.addTodo(
                Todo(
                    id = UUID.randomUUID().toString(),
                    title = "New Todo 1",
                    description = "New Description",
                )
            )
        }
    }

    fun toggleFilter() {
        showOnlyCompleted.value = !showOnlyCompleted.value
    }
}