package com.example.composechat.ui.todos

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.composechat.data.Todo
import com.example.composechat.ui.components.TodosAppBar


//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoContent(
    uiState: TodosUiState,
    navigateToTodoDetails: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        TodosAppBar(
            title = { Text(text = "Todos") },
        )

        if (uiState.todos.isEmpty()) {
            Text(
                text = "No Todos",
                style = MaterialTheme.typography.titleMedium,
            )
        } else {
            TodosList(
                uiState = uiState,
                navigateToTodoDetails = navigateToTodoDetails,
            )
        }
    }
}

@Composable
fun TodosList(
    uiState: TodosUiState,
    navigateToTodoDetails: (String) -> Unit,
) {
    LazyColumn {
        for (index in uiState.todos.indices) {
            item {
                val todo = uiState.todos[index]
                TodoItem(
                    onCompleteClick = { uiState.toggle(todo.id) },
                    todo = todo,
                    onTodoClick = navigateToTodoDetails
                )
                if (index < uiState.todos.size - 1) {
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.outline)
                    )
                }
            }
        }
    }
}


@Composable
fun TodoItem(
    onTodoClick: (String) -> Unit,
    onCompleteClick: (Boolean) -> Unit,
    todo: Todo
) {
    val backgroundColor = if (todo.isCompleted) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surface
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = backgroundColor)
        .clickable { onTodoClick(todo.id) }
        .padding(16.dp)
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = todo.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Checkbox(
            checked = todo.isCompleted,
            onCheckedChange = onCompleteClick
        )
    }
}