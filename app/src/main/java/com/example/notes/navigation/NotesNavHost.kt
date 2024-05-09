package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.ViewModel
import com.example.notes.screens.AddScreen
import com.example.notes.screens.MainScreen
import com.example.notes.screens.NoteScreen
import com.example.notes.screens.StartScreen
import com.example.notes.utils.Contants.Keys.ID
import com.example.notes.utils.Contants.Screens.ADD_SCREEN
import com.example.notes.utils.Contants.Screens.MAIN_SCREEN
import com.example.notes.utils.Contants.Screens.NOTE_SCREEN
import com.example.notes.utils.Contants.Screens.START_SCREEN

sealed class NavRoute(val route: String) {
    object Start: NavRoute(START_SCREEN)
    object Main: NavRoute(MAIN_SCREEN)
    object Add: NavRoute(ADD_SCREEN)
    object Note: NavRoute(NOTE_SCREEN)
}

@Composable
fun NotesNavHost(mViewModel: ViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{${ID}}") { backStackEntry ->
            NoteScreen(navController = navController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID))
        }
    }
}

