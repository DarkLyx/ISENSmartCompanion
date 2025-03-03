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

/*
package fr.isen.lucas.isensmartcompanion.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import fr.isen.lucas.isensmartcompanion.models.Conversation
import fr.isen.lucas.isensmartcompanion.databases.ConversationDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(onConversationClick: (Long) -> Unit) {
    val context = LocalContext.current
    val viewModel: HistoryViewModel = remember { HistoryViewModel(context) }
    val conversations by viewModel.conversations.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadConversations()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Historique des Conversations", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(conversations) { conversation ->
                ConversationItem(conversation, onDelete = { viewModel.deleteConversation(conversation.id) }) {
                    onConversationClick(conversation.id)
                }
            }
        }
    }
}

@Composable
fun ConversationItem(conversation: Conversation, onDelete: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = conversation.title, style = MaterialTheme.typography.titleMedium)
                Text(text = formatDate(conversation.date), style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Supprimer")
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
    return sdf.format(Date(timestamp))
}

class HistoryViewModel(context: android.content.Context) : ViewModel() {
    private val db = Room.databaseBuilder(
        context.applicationContext,
        ConversationDatabase::class.java,
        "conversation_database"
    ).fallbackToDestructiveMigration().build()

    private val dao = db.conversationDao()
    var conversations = mutableStateOf<List<Conversation>>(emptyList())

    fun loadConversations() {
        viewModelScope.launch {
            conversations.value = dao.getAllConversations()
        }
    }

    fun deleteConversation(conversationId: Long) {
        viewModelScope.launch {
            dao.deleteConversation(conversationId)
            loadConversations()
        }
    }
}

 */