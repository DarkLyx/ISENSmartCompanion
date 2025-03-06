package fr.isen.lucas.isensmartcompanion

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.Gumuchian.isensmartcompanion.screens.objects.BottomNavigationBar
import fr.isen.lucas.isensmartcompanion.models.TabBarItem
import fr.isen.lucas.isensmartcompanion.screens.*
import fr.isen.lucas.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")

        enableEdgeToEdge()
        setContent {
            Log.d("MainActivity", "setContent called")

            val homeTab = TabBarItem(title = stringResource(id = R.string.home), selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
            val eventsTab = TabBarItem(title = stringResource(id = R.string.events), selectedIcon = Icons.Filled.Info, unselectedIcon = Icons.Outlined.Info, badgeAmount = 7)
            val historyTab = TabBarItem(title = stringResource(id = R.string.history), selectedIcon = Icons.Filled.List, unselectedIcon = Icons.Outlined.List)
            val agendaTab = TabBarItem(title =  stringResource(id = R.string.agenda), selectedIcon = Icons.Filled.DateRange, unselectedIcon = Icons.Outlined.DateRange)

            val tabBarItems = listOf(homeTab, eventsTab, historyTab, agendaTab)

            val navController = rememberNavController()

            ISENSmartCompanionTheme {
                Scaffold(bottomBar = {
                    BottomNavigationBar(tabBarItems, navController)
                },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Log.d("MainActivity", "Scaffold content initialized")

                    Box(Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = homeTab.title) {
                            composable(homeTab.title) {
                                Log.d("MainActivity", "Navigating to HomeScreen")
                                HomeScreen()
                            }
                            composable(eventsTab.title) {
                                Log.d("MainActivity", "Navigating to EventsScreen")
                                EventsScreen()
                            }
                            composable(historyTab.title) {
                                Log.d("MainActivity", "Navigating to HistoryScreen")
                                HistoryScreen()
                            }
                            composable(agendaTab.title) {
                                Log.d("MainActivity", "Navigating to AgendaScreen")
                                AgendaScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    // Override lifecycle methods...
}
