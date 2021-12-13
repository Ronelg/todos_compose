package com.example.composechat.data

import java.util.*


val initialTodos = listOf(
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Clean the car",
        description = "Outside wash",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Fix bathroom door",
        description = "Call to service provider",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Black friday",
        description = "Shoos, Shirts and TV",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Buy groceries for dinner",
        description = "Salmon fish and Onions",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Mom birthday",
        description = "Love flowers",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Tech Radar Day",
        description = "Finish presentation",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Internet",
        description = "Check for lower prices",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Car yearly",
        description = "30K",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Feed the dog",
        description = "Only dry food",
    ),
    Todo(
        id = UUID.randomUUID().toString(),
        title = "Fix the window",
        description = "On service room",
    )
)

data class Todo(
    val id: String,
    val title: String,
    val description: String,
)