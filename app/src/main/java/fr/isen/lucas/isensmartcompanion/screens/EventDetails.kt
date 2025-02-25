package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import fr.isen.lucas.isensmartcompanion.models.Event

@Composable
fun EventDetails(backStackEntry: NavBackStackEntry, navController: androidx.navigation.NavHostController) {
    val eventJson = backStackEntry.arguments?.getString("eventJson")
    val event = remember { eventJson?.let { Gson().fromJson(it, Event::class.java) } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.popBackStack() }) {  // Utiliser le navController passé en paramètre
            Text("Retour")
        }

        Spacer(modifier = Modifier.height(16.dp))

        event?.let {
            Text(text = it.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.description, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "📅 ${it.date}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "📍 ${it.location}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Proposé par :  ${it.category}", fontSize = 16.sp)
        } ?: Text(text = "Événement introuvable", fontSize = 18.sp)
    }
}
