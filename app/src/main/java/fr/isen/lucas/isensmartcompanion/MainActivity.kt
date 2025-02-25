package fr.isen.lucas.isensmartcompanion

import android.os.Bundle
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
import androidx.compose.runtime.internal.composableLambdaN
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.lucas.isensmartcompanion.screens.EventsScreen
import fr.isen.lucas.isensmartcompanion.screens.HistoryScreen
import fr.isen.lucas.isensmartcompanion.screens.HomeScreen
import fr.isen.lucas.isensmartcompanion.screens.BottomNavigationBar
import fr.isen.lucas.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                    Box(Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = homeTab.title) {
                            composable(homeTab.title) {
                                HomeScreen(innerPadding)
                            }
                            composable(eventsTab.title) {
                                EventsScreen(innerPadding)
                            }
                            composable(historyTab.title) {
                                HistoryScreen(innerPadding)
                            }
                        }

                    }

                }
            }
        }
    }
}