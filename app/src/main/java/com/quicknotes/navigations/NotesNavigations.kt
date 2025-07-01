package com.quicknotes.navigations


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.quicknotes.screens.InsertNotesScreen
import com.quicknotes.screens.NotesScreen
import com.quicknotes.screens.SplashScreen

@Composable
fun NotesNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navHostController)
        }

        composable("home") {
            NotesScreen(navHostController)
        }

        composable("createNotes" + "/{id}") {
            val id = it.arguments?.getString("id")
            InsertNotesScreen(navHostController, id)
        }

    }
}