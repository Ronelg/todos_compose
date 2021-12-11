package com.example.composechat.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composechat.data.todosFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodosViewModel: ViewModel() {

    private val _todosUiState = MutableStateFlow(TodosUiState(emptyList()))
    val todosUiState = _todosUiState.asStateFlow()

    init {
        viewModelScope.launch {
            todosFlow.collect { todos ->
                _todosUiState.tryEmit(TodosUiState(todos))
            }
        }
    }

    fun addTodo() {
        _todosUiState.value.addTodo()
    }
}