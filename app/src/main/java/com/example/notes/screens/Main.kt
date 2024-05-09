package com.example.notes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.R
import com.example.notes.ViewModel
import com.example.notes.model.Note
import com.example.notes.navigation.NavRoute
import com.example.notes.ui.theme.NoTeSTheme

@Composable
fun MainScreen(navController: NavHostController, viewModel: ViewModel) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(70.dp),
                onClick = {
                navController.navigate(NavRoute.Add.route)
            }) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.Blue
                )
            }
        }
    ) { paddingValues ->

        Image(
            painter = painterResource(id = R.drawable.back2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn {
            items(notes) { note ->
                NoteItem(note = note, paddingValues = paddingValues, navController = navController)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, paddingValues: PaddingValues, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${note.id}")
            },
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = note.subtitle,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    NoTeSTheme {
        val context = LocalContext.current
        val mViewModel: ViewModel = viewModel()
        MainScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}