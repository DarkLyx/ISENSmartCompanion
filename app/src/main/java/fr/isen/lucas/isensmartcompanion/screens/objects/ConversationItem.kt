import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.lucas.isensmartcompanion.R
import fr.isen.lucas.isensmartcompanion.databases.AppDatabase
import fr.isen.lucas.isensmartcompanion.models.Conversation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ConversationItem(conversation: Conversation, db: AppDatabase, onConversationsUpdated: (List<Conversation>) -> Unit) {
    val dateFormatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    val formattedDate = dateFormatter.format(Date(conversation.date))
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(stringResource(id = R.string.date)+ " $formattedDate", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        Spacer(modifier = Modifier.height(5.dp))
        Text(stringResource(id = R.string.question)+" ${conversation.question}", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(id = R.string.answer)+" ${conversation.answer}", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.conversationDao().delete(conversation)
                        val updatedConversations = db.conversationDao().getAll()
                        onConversationsUpdated(updatedConversations)
                    }
                    Toast.makeText(context, "Message supprimé", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(stringResource(id = R.string.del_el), color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}
