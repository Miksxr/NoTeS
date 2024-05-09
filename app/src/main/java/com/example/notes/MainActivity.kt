package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.navigation.NotesNavHost
import com.example.notes.ui.theme.NoTeSTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoTeSTheme {

                val context = LocalContext.current
                val mViewModel: ViewModel = viewModel()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "NoTeS MiksXr",
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Text("Version: 1.0    ", color = MaterialTheme.colorScheme.primary)
                                }
                            },
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.primary)
                                .shadow(elevation = 12.dp)
                        )
                    },
                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            NotesNavHost(mViewModel)
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoTeSTheme {

    }
}