package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import fr.isen.lucas.isensmartcompanion.TabBarItem

@Composable
fun BottomNavigationBar(tabBarItems: List<TabBarItem>, navController: NavController) {
    NavigationBar {
        tabBarItems.forEach { tab ->
            NavigationBarItem(
                icon = { Icon(imageVector = tab.selectedIcon, contentDescription = tab.title) },
                label = { Text(tab.title) },
                selected = false,
                onClick = { navController.navigate(tab.title) }
            )
        }
    }
}
