package com.pemmob.pieplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemmob.pieplay.ui.screens.DetailScreen
import com.pemmob.pieplay.ui.screens.HomeScreen
import com.pemmob.pieplay.ui.theme.PiePlayTheme
import com.pemmob.pieplay.ui.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PiePlayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: GameViewModel = viewModel()
                    
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = viewModel, onGameClick = { gameId ->
                                navController.navigate("detail/$gameId")
                            })
                        }
                        composable(
                            "detail/{gameId}",
                            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val gameId = backStackEntry.arguments?.getInt("gameId") ?: return@composable
                            DetailScreen(
                                gameId = gameId,
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}