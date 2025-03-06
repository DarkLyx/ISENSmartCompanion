package fr.isen.Gumuchian.isensmartcompanion.screens.objects

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import fr.isen.lucas.isensmartcompanion.models.TabBarItem


@Composable
fun BottomNavigationBar(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTab by remember { mutableStateOf(tabBarItems.first().selectedIcon) }
    val selectedIconColor = MaterialTheme.colorScheme.primary

    NavigationBar {
        tabBarItems.forEach { tab ->
            val isSelected = selectedTab == tab.selectedIcon
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.title,
                        tint = if (isSelected) selectedIconColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        color = if (isSelected) selectedIconColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                },
                selected = isSelected,
                onClick = {
                    selectedTab = tab.selectedIcon
                    navController.navigate(tab.title)
                }
            )
        }
    }
}