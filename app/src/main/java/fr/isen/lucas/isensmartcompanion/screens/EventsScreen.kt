package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.colorResource
import fr.isen.lucas.isensmartcompanion.R

@Composable
fun EventsScreen() {
    val events = listOf(
        "Soirée BDE",
        "Gala ISEN",
        "Journée de Cohésion",
        "Hackathon ISEN",
        "Conférence Tech",
        "Sortie Karting",
        "Tournoi e-Sport"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.pink_background)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Événements ISEN",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
            ) {
                items(events) { event ->
                    EventItem(event)
                }
            }
        }

        FloatingActionButton(
            onClick = { /* Action à définir */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.Red
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plusicon),
                contentDescription = "Add Event",
                tint = Color.White
            )
        }
    }
}

@Composable
fun EventItem(eventName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.listlogo),
                contentDescription = "Event Icon",
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            ClickableText(
                text = AnnotatedString(eventName),
                style = LocalTextStyle.current.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                onClick = { /* Ajouter action pour chaque événement si besoin */ }
            )
        }
    }
}