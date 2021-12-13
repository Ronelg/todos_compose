package com.example.composechat.ui.todos

import com.example.composechat.data.Todo

data class TodosVieModelState(
    val completed: Set<String> = emptySet(),
    val todos: List<Todo> = emptyList()
) {

    fun toTodosUiState(): TodosUiState =
        if (todos.isEmpty()) {
            TodosUiState.NoTotods
        } else {
            TodosUiState.HasTotods(
                todos = todos,
                completed = completed
            )
        }

}

sealed interface TodosUiState {
    object NoTotods : TodosUiState

    data class HasTotods(
        val completed: Set<String>,
        val todos: List<Todo>
    ) : TodosUiState
}

