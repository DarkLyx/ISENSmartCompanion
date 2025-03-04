package fr.isen.Gumuchian.isensmartcompanion.screens.objects

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import fr.isen.lucas.isensmartcompanion.R
import fr.isen.lucas.isensmartcompanion.models.TabBarItem

@Composable
fun BottomNavigationBar(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTab by remember { mutableStateOf(tabBarItems.first().selectedIcon) }
    val selectedIconColor = colorResource(id = R.color.arrow_circle_color)

    NavigationBar {
        tabBarItems.forEach { tab ->
            val isSelected = selectedTab == tab.selectedIcon
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.title,
                        tint = if (isSelected) selectedIconColor else Color.Unspecified
                    )
                },
                label = { Text(tab.title) },
                selected = isSelected,
                onClick = {
                    selectedTab = tab.selectedIcon
                    navController.navigate(tab.title)
                }
            )
        }
    }
}
