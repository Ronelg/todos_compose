package com.example.composechat.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composechat.data.Todo
import com.example.composechat.data.TodosRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class TodosViewModel : ViewModel() {

    private val viewModelState = MutableStateFlow(TodosVieModelState())

    private val todosRepository = TodosRepository.getInstance()

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
                todosRepository.observeCompleted()
            ) { todos, completed ->
                viewModelState.update { it.copy(completed = completed, todos = todos) }
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
            TodosRepository.getInstance().addTodo(
                Todo(
                    id = UUID.randomUUID().toString(),
                    title = "New Todo 1",
                    description = "New Description",
                )
            )
        }
    }
}