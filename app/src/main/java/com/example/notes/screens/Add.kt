package com.example.notes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.notes.utils.Contants.Keys.ADD_NEW_NOTE
import com.example.notes.utils.Contants.Keys.ADD_NOTE
import com.example.notes.utils.Contants.Keys.NOTE_SUBTITLE
import com.example.notes.utils.Contants.Keys.NOTE_TITLE

@Composable
fun AddScreen(navController: NavHostController, viewModel: ViewModel) {

    var title by remember {mutableStateOf("")}
    var subtitle by remember {mutableStateOf("")}
    var isButtonEnabled by remember { mutableStateOf(false) }

    Image(
        painter = painterResource(id = R.drawable.back3),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 125.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = ADD_NEW_NOTE,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                            },
            label = { Text(text = NOTE_TITLE) },
            isError = title.isEmpty()
        )
        OutlinedTextField(
            value = subtitle,
            onValueChange = {
                subtitle = it
                isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                            },
            label = { Text(text = NOTE_SUBTITLE) },
            isError = subtitle.isEmpty()
        )
        Button(
            modifier = Modifier.padding(top = 20.dp),
            enabled = isButtonEnabled,
            onClick = {
                viewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                    navController.navigate(NavRoute.Main.route)
                }
            }
        ) {
            Text(
                fontSize = 20.sp,
                text = ADD_NOTE
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen() {
    NoTeSTheme {
        val context = LocalContext.current
        val mViewModel: ViewModel =
            viewModel()
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}