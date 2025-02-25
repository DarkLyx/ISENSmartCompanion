package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import fr.isen.lucas.isensmartcompanion.R
import fr.isen.lucas.isensmartcompanion.models.Event

@Composable
fun EventsScreen(navController: NavController) {
    val events = listOf(
        Event(1, "Soirée BDE", "Une soirée inoubliable organisée par le BDE !", "15 Mars 2025", "ISEN Toulon", "BDE"),
        Event(2, "Gala ISEN", "Le gala annuel de l'ISEN avec une ambiance chic et festive.", "25 Juin 2025", "Hôtel de Ville","BDE"),
        Event(3, "Journée de Cohésion", "Une journée d'activités pour renforcer l'esprit d'équipe.", "5 Septembre 2025", "Parc Naturel", "ISEN"),
        Event(4, "Hackathon ISEN", "Un hackathon intense de 48h pour innover en tech.", "12 Octobre 2025", "Campus ISEN", "ISEN Engineering"),
        Event(5, "Tournoi e-Sport", "Compétition gaming avec des équipes de l'ISEN.", "20 Novembre 2025", "Salle de jeux", "BDS"),
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(
                text = "Événements ISEN",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(events) { event ->
                    EventItem(event) {
                        // Sérialiser l'événement en JSON avec Gson
                        val eventJson = Gson().toJson(event)
                        // Naviguer vers l'écran de détails avec l'événement
                        navController.navigate("eventDetails/$eventJson")
                    }
                }
            }
        }
    }
}

@Composable
fun EventItem(event: Event, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = event.title,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
/*
package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import fr.isen.lucas.isensmartcompanion.R
import fr.isen.lucas.isensmartcompanion.models.Event

@Composable
fun EventsScreen(navController: NavController) {
    // Liste des événements gérée localement
    var events by remember { mutableStateOf(
        listOf(
            Event(1, "Soirée BDE", "Une soirée inoubliable organisée par le BDE !", "15 Mars 2025", "ISEN Toulon"),
            Event(2, "Gala ISEN", "Le gala annuel de l'ISEN avec une ambiance chic et festive.", "25 Juin 2025", "Hôtel de Ville"),
            Event(3, "Journée de Cohésion", "Une journée d'activités pour renforcer l'esprit d'équipe.", "5 Septembre 2025", "Parc Naturel"),
            Event(4, "Hackathon ISEN", "Un hackathon intense de 48h pour innover en tech.", "12 Octobre 2025", "Campus ISEN"),
            Event(5, "Tournoi e-Sport", "Compétition gaming avec des équipes de l'ISEN.", "20 Novembre 2025", "Salle de jeux")
        )
    )}

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(
                text = "Événements ISEN",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(events) { event ->
                    EventItem(event) {
                        // Sérialiser l'événement en JSON avec Gson
                        val eventJson = Gson().toJson(event)
                        // Naviguer vers l'écran de détails avec l'événement
                        navController.navigate("eventDetails/$eventJson")
                    }
                }
            }
        }

        // Ajouter le bouton flottant "+" en bas à droite de l'écran
        FloatingActionButton(
            onClick = { navController.navigate("newEvent") }, // Navigation vers la page de création d'événement
            modifier = Modifier
                .align(Alignment.BottomEnd)  // Positionner le bouton en bas à droite
                .padding(16.dp),
            containerColor = Color.Red
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plusicon),
                contentDescription = "Ajouter un événement",
                tint = Color.White
            )
        }
    }
}


@Composable
fun EventItem(event: Event, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = event.title,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

 */