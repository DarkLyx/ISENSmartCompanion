package fr.isen.lucas.isensmartcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.lucas.isensmartcompanion.screens.BottomNavigationBar
import fr.isen.lucas.isensmartcompanion.screens.EventsScreen
import fr.isen.lucas.isensmartcompanion.screens.HistoryScreen
import fr.isen.lucas.isensmartcompanion.screens.HomeScreen
import fr.isen.lucas.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ISENSmartCompanionTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}

@Composable
fun MainScreen(navController: androidx.navigation.NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController)
        }
    }
}

@Composable
fun NavigationGraph(navController: androidx.navigation.NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("events") { EventsScreen() }
        composable("history") { HistoryScreen() }
    }
}
