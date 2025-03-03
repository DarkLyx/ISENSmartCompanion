package fr.isen.lucas.isensmartcompanion.models

import androidx.compose.ui.graphics.vector.ImageVector;


data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)