package fr.isen.lucas.isensmartcompanion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.lucas.isensmartcompanion.models.TabBarItem
import fr.isen.lucas.isensmartcompanion.screens.EventsScreen
import fr.isen.lucas.isensmartcompanion.screens.HistoryScreen
import fr.isen.lucas.isensmartcompanion.screens.HomeScreen
import fr.isen.lucas.isensmartcompanion.screens.objects.BottomNavigationBar
import fr.isen.lucas.isensmartcompanion.ui.theme.ISENSmartCompanionTheme



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")

        enableEdgeToEdge()
        setContent {
            Log.d("MainActivity", "setContent called")

            val homeTab = TabBarItem(title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
            val eventsTab = TabBarItem(title = "Events", selectedIcon = Icons.Filled.DateRange, unselectedIcon = Icons.Outlined.DateRange, badgeAmount = 7)
            val historyTab = TabBarItem(title = "History", selectedIcon = Icons.Filled.List, unselectedIcon = Icons.Outlined.List)

            val tabBarItems = listOf(homeTab, eventsTab, historyTab)

            val navController = rememberNavController()

            ISENSmartCompanionTheme {
                Scaffold( bottomBar = {
                    BottomNavigationBar(tabBarItems, navController)
                },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Log.d("MainActivity", "Scaffold content initialized")

                    Box(Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = homeTab.title) {
                            composable(homeTab.title) {
                                Log.d("MainActivity", "Navigating to HomeScreen")
                                HomeScreen(innerPadding)
                            }
                            composable(eventsTab.title) {
                                Log.d("MainActivity", "Navigating to EventsScreen")
                                EventsScreen(innerPadding)
                            }
                            composable(historyTab.title) {
                                Log.d("MainActivity", "Navigating to HistoryScreen")
                                HistoryScreen(innerPadding)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }
}
