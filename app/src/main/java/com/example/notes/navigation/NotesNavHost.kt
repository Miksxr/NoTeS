package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.screens.AddScreen
import com.example.notes.screens.MainScreen
import com.example.notes.screens.NoteScreen
import com.example.notes.screens.StartScreen

sealed class NavRoute(val route: String) {
    object Start: NavRoute("start_screen")
    object Main: NavRoute("main_screen")
    object Add: NavRoute("add_screen")
    object Note: NavRoute("note_screen")
}

@Composable
fun NotesNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController)}
        composable(NavRoute.Main.route) { MainScreen(navController = navController)}
        composable(NavRoute.Add.route) { AddScreen(navController = navController)}
        composable(NavRoute.Note.route) { NoteScreen(navController = navController)}
    }
}

