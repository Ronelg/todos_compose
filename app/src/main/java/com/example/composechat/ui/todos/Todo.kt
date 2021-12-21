package com.example.composechat.ui.todos

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composechat.data.Todo
import com.example.composechat.data.initialTodos
import com.example.composechat.theme.TodosTheme
import com.example.composechat.ui.components.TodosAppBar
import java.util.*


@Composable
fun TodosScreen(
    uiState: TodosUiState,
    navigateToTodoDetails: (String) -> Unit,
    onToggleComplete: (String) -> Unit,
    onAddTodo: () -> Unit,
    onToggleFilter: () -> Unit
) {
    Scaffold(
        topBar = {
            TodosAppBar(
                title = { Text(text = "Todos") },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.FilterList,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .clickable(onClick = onToggleFilter)
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                            .height(24.dp),
                        contentDescription = "stringResource(id = R.string.search"
                    )
                }
            )
        },
        content = {
            TodoContent(
                uiState = uiState,
                onToggleComplete = onToggleComplete,
                navigateToTodoDetails = navigateToTodoDetails
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTodo,
                shape = RectangleShape,
                content = { androidx.compose.material.Icon(Icons.Filled.Add, "") }
            )
        }
    )
}

@Composable
fun TodoContent(
    uiState: TodosUiState,
    navigateToTodoDetails: (String) -> Unit,
    onToggleComplete: (String) -> Unit
) {
    when (uiState) {
        is TodosUiState.HasTotods -> {
            TodosList(
                uiState = uiState,
                onToggleComplete = onToggleComplete,
                navigateToTodoDetails = navigateToTodoDetails,
            )
        }
        TodosUiState.NoTotods -> {
            Text(
                text = "No Todos",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
fun TodosList(
    uiState: TodosUiState.HasTotods,
    onToggleComplete: (String) -> Unit,
    navigateToTodoDetails: (String) -> Unit,
) {
    LazyColumn {
        for (index in uiState.todos.indices) {
            item {
                val todo = uiState.todos[index]
                TodoItem(
                    onToggleComplete = { onToggleComplete(uiState.todos[index].id) },
                    todo = todo,
                    isComplete = uiState.completed.contains(uiState.todos[index].id),
                    onTodoClick = navigateToTodoDetails
                )
                if (index < uiState.todos.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun TodoItem(
    onTodoClick: (String) -> Unit,
    onToggleComplete: (Boolean) -> Unit,
    todo: Todo,
    isComplete: Boolean
) {
    val backgroundColor = if (isComplete) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surface
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(8.dp)))
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
            checked = isComplete,
            onCheckedChange = onToggleComplete
        )
    }
}

@Preview(name = "TodoItemPreview")
@Preview(name = "TodoItemPreviewDark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TodoItemPreview() {
    TodosTheme {
        TodoItem(
            onTodoClick = { },
            onToggleComplete = {},
            todo = initialTodos[2],
            isComplete = false
        )
    }
}

@Preview(name = "TodoItemSelectedPreview")
@Preview(name = "TodoItemSelectedPreview", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TodoItemSelectedPreview() {
    TodosTheme {
        TodoItem(
            onTodoClick = { },
            onToggleComplete = {},
            todo = initialTodos[1],
            isComplete = true
        )
    }
}

@Composable
fun TodoItem2(
    todo: Todo,
    isComplete: Boolean
) {

    val backgroundColor = if (isComplete) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surface
    }

    var isCheched by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(8.dp)))
            .background(color = backgroundColor)
//        .clickable { onTodoClick(todo.id) }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = todo.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = todo.description)
        }
        Checkbox(
            checked = isCheched,
            onCheckedChange = { isCheched = it }
        )
    }
}

@Preview()
@Composable
fun P4() {
    TodosTheme {
        TodoItem2(
            todo = Todo(
                id = UUID.randomUUID().toString(),
                title = "Title",
                description = "Description"
            ),
            isComplete = true
        )
    }
}

@Preview
@Composable
fun TodosListPreview() {
    TodosTheme {
        TodosList(
            uiState = TodosUiState.HasTotods(emptySet(), initialTodos),
            onToggleComplete = {},
            navigateToTodoDetails = {}
        )
    }
}
