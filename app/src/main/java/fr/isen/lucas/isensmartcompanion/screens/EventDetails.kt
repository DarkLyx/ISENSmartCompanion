package fr.isen.lucas.isensmartcompanion.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.lucas.isensmartcompanion.R
import fr.isen.lucas.isensmartcompanion.models.Event


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetails(event: Event) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Détails de l'événement") },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowback),
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
        ) {
            Text(text = event.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = event.description, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "📅 ${event.date}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "📍 ${event.location}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Proposé par : ${event.category}", fontSize = 16.sp)
        }
    }
}
