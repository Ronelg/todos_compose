package com.example.composechat.ui.todos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.composechat.R

class TodosFragment : Fragment() {

    private val viewModel: TodosViewModel by viewModels()

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
            val todosUiState by viewModel.uiState.collectAsState()

            TodosScreen(
                uiState = todosUiState,
                navigateToTodoDetails = { id ->
                    val bundle = bundleOf("id" to id)
                    findNavController().navigate(
                        R.id.navigation_todo_details,
                        bundle
                    )
                },
                onToggleComplete = {id -> viewModel.toggleTodo(id)},
                onAddTodo = {viewModel.addTodo()},
                onToggleFilter = { viewModel.toggleFilter()}

            )
        }
    }
}


