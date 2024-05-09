package com.example.notes.screens

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.R
import com.example.notes.ViewModel
import com.example.notes.navigation.NavRoute
import com.example.notes.ui.theme.NoTeSTheme
import com.example.notes.utils.TYPE_FIREBASE
import com.example.notes.utils.TYPE_ROOM

@Composable
fun StartScreen(navController: NavHostController, viewModel: ViewModel) {

    val context = LocalContext.current
    val mViewModel: ViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {paddingValues ->

        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                mViewModel.initDatabase(TYPE_ROOM) {
                    navController.navigate(route = NavRoute.Main.route)
                }
            },
                modifier = Modifier
                    .width(280.dp)
                ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    text = "Заводи шарманку Глускер, мы начинаем!"
                )
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