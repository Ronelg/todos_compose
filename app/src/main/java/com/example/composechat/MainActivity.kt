package com.example.composechat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberScaffoldState
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.composechat.databinding.ContentMainBinding
import com.example.composechat.ui.components.TodosScaffold

class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val scaffoldState = rememberScaffoldState()

            TodosScaffold(
                scaffoldState = scaffoldState
            ) {
                AndroidViewBinding(ContentMainBinding::inflate)
            }
        }
    }
}