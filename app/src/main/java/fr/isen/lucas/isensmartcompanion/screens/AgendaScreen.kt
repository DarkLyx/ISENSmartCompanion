package fr.isen.lucas.isensmartcompanion.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.isen.lucas.isensmartcompanion.R
import fr.isen.lucas.isensmartcompanion.models.Course
import fr.isen.lucas.isensmartcompanion.models.Event
import fr.isen.lucas.isensmartcompanion.screens.objects.CourseCalendar
import fr.isen.lucas.isensmartcompanion.screens.objects.EventCalendar
import fr.isen.lucas.isensmartcompanion.services.EventApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AgendaScreen() {
    val eventApiService = remember {
        Retrofit.Builder()
            .baseUrl("https://isen-smart-companion-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventApiService::class.java)
    }

    var events by remember { mutableStateOf<List<Event>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = eventApiService.getEvents()
                if (response.isSuccessful) {
                    events = response.body()?.take(3)?.sortedBy { it.date.toDate() } ?: emptyList()
                } else {
                    Log.e("AgendaScreen", "API Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AgendaScreen", "Error fetching events: ${e.message}")
            }
        }
    }

    val courses = listOf(
        Course("Math 101", "Intro to Math", "2025-03-10", "08:00 - 10:00"),
        Course("CS 201", "Data Structures", "2025-03-11", "14:00 - 16:00"),
        Course("Physics 101", "Intro to Physics", "2025-03-12", "10:00 - 12:00"),
        Course("Chemistry 101", "Intro to Chemistry", "2025-03-13", "13:00 - 15:00")
    ).take(4).sortedBy { it.date.toDate() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.AgendaTitle),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.Courses), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        courses.forEach { CourseCalendar(it) }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = R.string.Events), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        if (events.isEmpty()) {
            Text("Pas d'évenement à venir.", style = MaterialTheme.typography.bodyMedium)
        } else {
            events.forEach { EventCalendar(it) }
        }
    }
}




fun String.toDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.parse(this) ?: Date()
}
