package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.isen.lucas.isensmartcompanion.models.Event

@Composable
fun NewEvent(navController: NavController) {
    // Variables d'état pour les champs du formulaire
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf(TextFieldValue("")) }
    var location by remember { mutableStateOf(TextFieldValue("")) }
    var category by remember { mutableStateOf(TextFieldValue("")) }


        Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ajouter un événement",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Champs de saisie pour les informations de l'événement
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Lieu") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Bouton pour ajouter l'événement
        Button(
            onClick = {
                // Créer un nouvel événement
                val newEvent = Event(
                    id = (1..1000).random(), // ID généré aléatoirement pour l'exemple
                    title = title.text,
                    description = description.text,
                    date = date.text,
                    location = location.text,
                    category = category.text
                )
                // Vous pouvez ajouter l'événement à une base de données ici
                navController.popBackStack() // Retourner à l'écran précédent après l'ajout
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ajouter l'événement")
        }
    }
}
/*
package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.isen.lucas.isensmartcompanion.models.Event

@Composable
fun NewEvent(navController: NavController, addEvent: (Event) -> Unit) {
    // Variables d'état pour les champs du formulaire
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf(TextFieldValue("")) }
    var location by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ajouter un événement",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Champs de saisie pour les informations de l'événement
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Lieu") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Bouton pour ajouter l'événement
        Button(
            onClick = {
                // Créer un nouvel événement
                val newEvent = Event(
                    id = (1..1000).random(), // ID généré aléatoirement pour l'exemple
                    title = title.text,
                    description = description.text,
                    date = date.text,
                    location = location.text
                )
                addEvent(newEvent) // Ajouter l'événement à la liste via le callback
                navController.popBackStack() // Retourner à l'écran précédent après l'ajout
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ajouter l'événement")
        }
    }
}

 */