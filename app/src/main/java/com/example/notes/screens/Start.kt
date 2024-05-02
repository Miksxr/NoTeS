package com.example.notes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.ViewModel
import com.example.notes.navigation.NavRoute
import com.example.notes.ui.theme.NoTeSTheme
import com.example.notes.utils.TYPE_FIREBASE
import com.example.notes.utils.TYPE_ROOM

@Composable
fun StartScreen(navController: NavHostController, viewModel: ViewModel) {

    val context = LocalContext.current
    val mViewModel: ViewModel =
        viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Выбери вид хранения данных!")
            Button(onClick = {
                mViewModel.initDatabase(TYPE_ROOM) {
                    navController.navigate(route = NavRoute.Main.route)
                }
            },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
                ) {
                Text(text = "Room database")
            }
            Button(onClick = {
                mViewModel.initDatabase(TYPE_FIREBASE) {
                    navController.navigate(route = NavRoute.Main.route)
                }
            },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Firebase database")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    NoTeSTheme {
        val context = LocalContext.current
        val mViewModel: ViewModel =
            viewModel()
        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}