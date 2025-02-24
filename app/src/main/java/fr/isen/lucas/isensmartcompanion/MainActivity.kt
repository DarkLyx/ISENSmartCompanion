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
import fr.isen.lucas.isensmartcompanion.screens.EventDetails
import fr.isen.lucas.isensmartcompanion.screens.EventsScreen
import fr.isen.lucas.isensmartcompanion.screens.HistoryScreen
import fr.isen.lucas.isensmartcompanion.screens.HomeScreen
import fr.isen.lucas.isensmartcompanion.ui.theme.ISENSmartCompanionTheme
import fr.isen.lucas.isensmartcompanion.screens.NewEvent
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
        composable("events") { EventsScreen(navController) }
        composable("history") { HistoryScreen() }
        composable("eventDetails/{eventJson}") { backStackEntry ->
            EventDetails(backStackEntry, navController)  // Passer le navController
        }
        composable("newEvent") {
            NewEvent(navController) // Page de création d'un nouvel événement
        }
    }
}



/*
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
import fr.isen.lucas.isensmartcompanion.models.Event
import fr.isen.lucas.isensmartcompanion.screens.BottomNavigationBar
import fr.isen.lucas.isensmartcompanion.screens.EventDetails
import fr.isen.lucas.isensmartcompanion.screens.EventsScreen
import fr.isen.lucas.isensmartcompanion.screens.HistoryScreen
import fr.isen.lucas.isensmartcompanion.screens.HomeScreen
import fr.isen.lucas.isensmartcompanion.ui.theme.ISENSmartCompanionTheme
import fr.isen.lucas.isensmartcompanion.screens.NewEvent
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
        composable("events") { EventsScreen(navController) }
        composable("history") { HistoryScreen() }
        composable("eventDetails/{eventJson}") { backStackEntry ->
            EventDetails(backStackEntry, navController)  // Passer le navController
        }
        composable("newEvent") {
            NewEvent(navController) { newEvent ->
                // Callback qui ajoute l'événement à la liste d'événements
                (navController.previousBackStackEntry?.savedStateHandle?.get<List<Event>>("events") as? MutableList)?.add(newEvent)
                navController.popBackStack()  // Retourner à l'écran précédent après l'ajout
            }
        }
    }
}




 */