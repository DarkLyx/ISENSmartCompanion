package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import fr.isen.lucas.isensmartcompanion.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.homelogo), contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.listlogo), contentDescription = "Events") },
            label = { Text("Events") },
            selected = false,
            onClick = { navController.navigate("events") }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.historylogo), contentDescription = "History") },
            label = { Text("History") },
            selected = false,
            onClick = { navController.navigate("history") }
        )
    }
}