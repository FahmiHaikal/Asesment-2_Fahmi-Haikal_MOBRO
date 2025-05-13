package com.fahmi0003.asesmen2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fahmi0003.asesmen2.ui.screen.AboutScreen
import com.fahmi0003.asesmen2.ui.screen.DetailScreen
import com.fahmi0003.asesmen2.ui.screen.HausScreen
import com.fahmi0003.asesmen2.ui.screen.HomeScreen
import com.fahmi0003.asesmen2.ui.screen.MainScreen
import com.fahmi0003.asesmen2.ui.screen.MenuScreen

sealed class Screen(val route: String) {
    data object Home: Screen("homeScreen")
    data object Main: Screen("mainScreen")
    data object Data: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object About: Screen("aboutScreen")
    data object Haus: Screen("hausScreen")
    data object Menu: Screen("menuScreen")
    data object FormUbah : Screen("detailScreen/{id}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Data.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Haus.route) {
            HausScreen(navController)
        }
        composable(route = Screen.Menu.route) {
            MenuScreen(navController)
        }
        composable(route = Screen.Data.route) {
            MainScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val id = arguments.getLong("id")
            DetailScreen(navController, id)
        }
    }
}