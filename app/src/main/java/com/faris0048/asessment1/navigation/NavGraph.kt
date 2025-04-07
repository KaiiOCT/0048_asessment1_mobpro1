package com.faris0048.asessment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.faris0048.asessment1.ui.screen.AboutBeratScreen
import com.faris0048.asessment1.ui.screen.AboutPanjangScreen
import com.faris0048.asessment1.ui.screen.BeratScreen
import com.faris0048.asessment1.ui.screen.MainScreen
import com.faris0048.asessment1.ui.screen.PanjangScreen
import com.faris0048.asessment1.ui.screen.SuhuScreen
import com.faris0048.mobpro1.ui.screen.AboutSuhuScreen

@Composable
fun SetUpNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Suhu.route) {
            SuhuScreen(navController)
        }
        composable(route = Screen.Panjang.route) {
            PanjangScreen(navController)
        }
        composable (route = Screen.Berat.route) {
            BeratScreen(navController)
        }
        composable (route = Screen.AboutSuhu.route) {
            AboutSuhuScreen(navController)
        }
        composable(route = Screen.AboutPanjang.route) {
            AboutPanjangScreen(navController)
        }
        composable(route = Screen.AboutBerat.route) {
            AboutBeratScreen(navController)
        }

    }
}