package fr.isen.lucas.isensmartcompanion.screens

import ConversationItem
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import fr.isen.lucas.isensmartcompanion.R
import fr.isen.lucas.isensmartcompanion.databases.AppDatabase
import fr.isen.lucas.isensmartcompanion.models.Conversation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "Conversation_DB"
    ).build()

    var conversations by remember { mutableStateOf<List<Conversation>>(emptyList()) }

    LaunchedEffect(Unit) {
        conversations = db.conversationDao().getAll()
    }

    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.pink_background)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Historique des Conversations",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )

                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            db.conversationDao().deleteAll()
                            val updatedConversations = db.conversationDao().getAll()
                            conversations = updatedConversations
                        }
                        Toast.makeText(context, "Historique effacé", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .background( colorResource(id = R.color.arrow_circle_color), shape = RoundedCornerShape(20))
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trashlogo),
                        contentDescription = "Supprimer l'historique",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(conversations) { conversation ->
                    ConversationItem(conversation, db) { updatedConversations ->
                        conversations = updatedConversations
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
