package fr.isen.lucas.isensmartcompanion.screens.objects

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme

import fr.isen.lucas.isensmartcompanion.models.Conversation
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageBubble(message: Conversation) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (message.question.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = convertToParisTime(message.date),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = message.question,
                            fontSize = 16.sp,
                            color =  MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
            if (message.answer.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                    ),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = convertToParisTime(message.date),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                        Text(
                            text = message.answer,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertToParisTime(timestamp: Long): String {
    val instant = Instant.ofEpochMilli(timestamp)
    val parisTime = instant.atZone(ZoneId.of("Europe/Paris"))
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")
    return parisTime.format(formatter)
}
