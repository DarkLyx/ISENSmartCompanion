package fr.isen.lucas.isensmartcompanion.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.lucas.isensmartcompanion.EventDetailsActivity
import fr.isen.lucas.isensmartcompanion.models.Event
import fr.isen.lucas.isensmartcompanion.screens.objects.EventItem
import fr.isen.lucas.isensmartcompanion.services.EventApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun EventsScreen() {
    val context = LocalContext.current

    var events by remember { mutableStateOf<List<Event>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val eventApiService = remember {
        Retrofit.Builder()
            .baseUrl("https://isen-smart-companion-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventApiService::class.java)
    }

    LaunchedEffect(Unit) {
        try {
            val response = withContext(Dispatchers.IO) { eventApiService.getEvents() }
            if (response.isSuccessful) {
                response.body()?.let {
                    events = it
                } ?: run {
                    errorMessage = "Réponse vide de l'API"
                }
            } else {
                errorMessage = "Erreur API : ${response.code()}"
            }
        } catch (e: Exception) {
            errorMessage = "Erreur de connexion : ${e.message}"
            Log.e("EventsScreen", "Erreur lors de la récupération des événements", e)
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Événements ISEN",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            when {
                isLoading -> CircularProgressIndicator()
                errorMessage != null -> Text("Erreur : $errorMessage", color = MaterialTheme.colorScheme.error)
                events.isEmpty() -> Text("Aucun événement disponible.")
                else -> LazyColumn {
                    items(events) { event ->
                        EventItem(event) {
                            val intent = Intent(context, EventDetailsActivity::class.java).apply {
                                putExtra("event", event)
                            }
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}


