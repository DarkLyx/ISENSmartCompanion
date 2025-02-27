package fr.isen.lucas.isensmartcompanion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.lucas.isensmartcompanion.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.ai.client.generativeai.GenerativeModel

data class Message(val text: String, val isUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    var textState by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Message>() }
    val listState = rememberLazyListState()
    val conversationHistory = remember { mutableStateListOf<String>() }
    var chatTitle by remember { mutableStateOf("Nouvelle conversation") }

    val model = remember {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "AIzaSyC-zec56p3RBFzR1UCxHc208otAKWfYtVY" // Remplace par ta clé API
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.pink_background)),
           // .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // En-tête avec le logo et le bouton "New Chat"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.isenlogo),
                    contentDescription = "ISEN Logo",
                    modifier = Modifier.size(80.dp)
                )

                Button(
                    onClick = {
                        messages.clear()
                        conversationHistory.clear()
                        chatTitle = "Nouvelle conversation"
                        textState = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("New Chat", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Titre de la conversation généré par IA
            Text(
                chatTitle,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Zone de conversation
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(messages) { message ->
                        MessageBubble(message)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Barre de saisie et bouton d'envoi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.text_area_color), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = textState,
                    onValueChange = { textState = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Écris un message...") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(colorResource(id = R.color.arrow_circle_color), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            if (textState.isNotBlank()) {
                                val userMessage = textState
                                messages.add(Message(userMessage, isUser = true))
                                conversationHistory.add("Utilisateur: $userMessage")
                                textState = ""

                                CoroutineScope(Dispatchers.IO).launch {
                                    val fullContext = conversationHistory.joinToString("\n")
                                    val response = model.generateContent(fullContext)
                                    val botResponse = response.text ?: "Je n'ai pas compris..."

                                    messages.add(Message(botResponse, isUser = false))
                                    conversationHistory.add("IA: $botResponse")

                                    // Génération du titre de conversation si c'est le premier message
                                    if (messages.size == 2) {
                                        val titleResponse = model.generateContent("Génère un titre court résumant cette conversation :\n$fullContext")
                                        chatTitle = titleResponse.text ?: "Nouvelle conversation"
                                    }
                                }
                            }
                        },
                        modifier = Modifier.size(36.dp),
                        enabled = textState.isNotBlank()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowforward),
                            contentDescription = "Send",
                            tint = if (textState.isNotBlank()) Color.White else Color.Gray
                        )
                    }
                }
            }
        }
    }
}

// Affichage des messages avec style différent pour l'utilisateur et l'IA
@Composable
fun MessageBubble(message: Message) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser) Color(0xFFBBDEFB) else Color(0xFFE0E0E0)
            ),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}
