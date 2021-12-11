package com.example.composechat.ui.todos

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.example.composechat.R
import com.example.composechat.data.Todo
import com.example.composechat.data.todosUiState
import com.example.composechat.theme.TodosTheme
import com.example.composechat.ui.components.TodosScaffold

class TodosFragment : Fragment() {

    val viewModel: TodosViewModel by viewModels()

    @ExperimentalMaterial3Api
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContent {
            val todosUiState by viewModel.todosUiState.collectAsState()

            Scaffold(
                content = {
                    TodoContent(
                        uiState = todosUiState,
                        navigateToTodoDetails = { id ->
                            val bundle = bundleOf("id" to id)
                            findNavController().navigate(
                                R.id.navigation_todo_details,
                                bundle
                            )
                        },
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { viewModel.addTodo() },
                        shape = RectangleShape,
                        content = { Icon(Icons.Filled.Add, "") }
                    )
                }
            )
        }
    }
}

@Preview(name  = "TodoItemPreview" )
@Preview(name  = "TodoItemPreviewDark", uiMode = UI_MODE_NIGHT_YES )
@Composable
fun TodoItemPreview() {
    TodosTheme {
        TodoItem(
            onTodoClick = { },
            onCompleteClick = {},
            todo = todosUiState.todos[2]
        )
    }
}


@Preview(name  = "TodoItemSelectedPreview" )
@Preview(name  = "TodoItemSelectedPreview", uiMode = UI_MODE_NIGHT_YES )
@Composable
fun TodoItemSelectedPreview() {
    TodosTheme {
        TodoItem(
            onTodoClick = { },
            onCompleteClick = {},
            todo = todosUiState.todos[1]
        )
    }
}
