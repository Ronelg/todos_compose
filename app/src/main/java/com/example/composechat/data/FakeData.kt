package com.example.composechat.data

import com.example.composechat.ui.todos.TodosUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import java.util.*


private val initialTodos = listOf(
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Clean the car",
        description = "Outside wash",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Fix bathroom door",
        description = "Call to service provider",
        timestamp = "8:05 PM",
        isCompleted = true,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Black friday",
        description = "Shoos, Shirts and TV",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Buy groceries for dinner",
        description = "Salmon fish and Onions",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Mom birthday",
        description = "Love flowers",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Tech Radar Day",
        description = "Finish presentation",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Internet",
        description = "Check for lower prices",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Car yearly",
        description = "30K",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Feed the dog",
        description = "Only dry food",
        timestamp = "8:05 PM",
        isCompleted = false,
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Fix the window",
        description = "On service room",
        timestamp = "8:05 PM",
        isCompleted = false,
    )
)

val todosFlow = MutableSharedFlow<List<Todo>>(replay = 1).apply {
    tryEmit(initialTodos)
}

val todosUiState = TodosUiState(
    initialTodos = initialTodos
)

data class Todo(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: String,
    val isCompleted: Boolean,
)