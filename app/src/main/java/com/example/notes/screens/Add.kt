package com.example.notes.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.navigation.NavRoute
import com.example.notes.ui.theme.NoTeSTheme

@Composable
fun AddScreen(navController: NavHostController) {

    var title by remember {mutableStateOf("")}
    var subtitle by remember {mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Add new note",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text(text = "Note title") }
        )
        OutlinedTextField(
            value = subtitle,
            onValueChange = {subtitle = it},
            label = { Text(text = "Note subtitle") }
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                navController.navigate(NavRoute.Main.route)
            }
        ) {
            Text(text = "Add note")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen() {
    NoTeSTheme {
        AddScreen(navController = rememberNavController())
    }
}