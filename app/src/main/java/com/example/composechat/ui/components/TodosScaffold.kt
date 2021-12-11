package com.example.composechat.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldState
import androidx.compose.material3.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.example.composechat.theme.TodosTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodosScaffold(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable (PaddingValues) -> Unit
) {
    TodosTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            content = content,
        )
    }
}