package com.example.composechat.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composechat.theme.TodosTheme

@Composable
fun TodosAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {

    val backgroundColor = MaterialTheme.colorScheme.primary
    val foregroundColors = contentColorFor(backgroundColor)

    Box {
        TopAppBar(
            modifier = modifier,
            actions = actions,
            backgroundColor = backgroundColor,
            contentColor = foregroundColors,
            title = title,
            elevation = 2.dp
        )
    }
}

@Preview
@Composable
fun JetchatAppBarPreview() {
    TodosTheme {
        TodosAppBar(title = { Text("Preview!") })
    }
}

@Preview
@Composable
fun JetchatAppBarPreviewDark() {
    TodosTheme(isDarkTheme = true) {
        TodosAppBar(title = { Text("Preview!") })
    }
}