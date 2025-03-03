package fr.isen.lucas.isensmartcompanion.screens.objects

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
import fr.isen.lucas.isensmartcompanion.models.Conversation


@Composable
fun MessageBubble(message: Conversation) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = if (message.question.isNotEmpty()) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (message.question.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFBBDEFB) // Bleu pour la question
                    ),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = message.date.toString(),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = message.question,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
            }

            if (message.answer.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE0E0E0) // Gris pour la réponse
                    ),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = message.date.toString(),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = message.answer,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
