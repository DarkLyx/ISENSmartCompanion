package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
@Composable
fun HistoryScreen(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // Ajout du padding pour éviter le chevauchement avec la BottomNavigationBar
        contentAlignment = Alignment.Center
    ) {
        Text(text = "History Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}
