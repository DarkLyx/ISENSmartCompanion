package fr.isen.lucas.isensmartcompanion.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import fr.isen.lucas.isensmartcompanion.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.ai.client.generativeai.GenerativeModel
import fr.isen.lucas.isensmartcompanion.databases.AppDatabase
import fr.isen.lucas.isensmartcompanion.models.Conversation
import fr.isen.lucas.isensmartcompanion.screens.objects.MessageBubble
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    var textState by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Conversation>() }
    val listState = rememberLazyListState()
    var chatTitle by remember { mutableStateOf("Nouvelle conversation") }
    val chatbase= stringResource(id = R.string.new_conv)
    val context = LocalContext.current

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "Conversation_DB"
    ).build()

    val model = remember {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = ""
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
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
                        chatTitle = chatbase
                        textState = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(stringResource(id = R.string.new_chat), color = MaterialTheme.colorScheme.onPrimary)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                chatTitle,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = textState,
                    onValueChange = { textState = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text( stringResource(id = R.string.place_holder) ) },
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            if (textState.isNotBlank()) {
                                val userMessage = textState
                                val currentDate = System.currentTimeMillis()
                                val conversation = Conversation(
                                    title = chatTitle,
                                    date = currentDate,
                                    question = userMessage,
                                    answer = ""
                                )

                                textState = ""
                                messages.add(conversation)
                                CoroutineScope(Dispatchers.IO).launch {
                                    try {
                                        val fullContext = messages.joinToString("\n") { it.question + "\n" + it.answer }
                                        val response = model.generateContent(fullContext)
                                        val botResponse = response.text ?: "Je n'ai pas compris..."

                                        val updatedConversation = conversation.copy(answer = botResponse)
                                        messages[messages.size - 1] = updatedConversation
                                        db.conversationDao().insert(updatedConversation)

                                        if (messages.size >= 1) {
                                            val titleResponse = model.generateContent("Génère un titre court résumant cette conversation :\n$fullContext")
                                            chatTitle = titleResponse.text ?: "Nouvelle conversation"
                                        }
                                    } catch (e: Exception) {
                                        val errorResponse = "Désolé, je n'ai pas pu répondre à cette question."
                                        val updatedConversation = conversation.copy(answer = errorResponse)
                                        messages[messages.size - 1] = updatedConversation
                                        db.conversationDao().insert(updatedConversation)
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
                            tint = if (textState.isNotBlank()) MaterialTheme.colorScheme.onPrimary else Color.Gray
                        )
                    }
                }
            }
        }
    }
}
