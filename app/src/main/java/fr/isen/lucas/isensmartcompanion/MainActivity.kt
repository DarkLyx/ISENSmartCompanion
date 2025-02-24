package fr.isen.lucas.isensmartcompanion

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.lucas.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ISENSmartCompanionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.pink_background)
                ) {
                    AssistantUI()
                }
            }
        }
    }
}

@Composable
fun AssistantUI() {
    val context = LocalContext.current

    var question by remember { mutableStateOf(TextFieldValue("")) }
    var response by remember { mutableStateOf("") }
    var previousQuestion by remember { mutableStateOf("") } // Save the last question

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top section: Logo
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.isenlogo),
                contentDescription = "Logo ISEN",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Middle section: Display the last entered question
        if (previousQuestion.isNotEmpty()) {
            Text(
                text = "Dernière question : $previousQuestion",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Bottom section: Input field and send button
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.text_area_color), shape = MaterialTheme.shapes.medium)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = question,
                        onValueChange = { question = it },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            if (question.text.isEmpty()) {
                                Text("Posez-moi une question...", color = Color.Gray, fontSize = 18.sp)
                            }
                            innerTextField()
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            if (question.text.isNotEmpty()) {
                                previousQuestion = question.text // Save the last question
                                response = "Réponse de l'IA : ${question.text}"
                                Toast.makeText(context, "Question envoyée : ${question.text}", Toast.LENGTH_SHORT).show()
                                question = TextFieldValue("") // Clear input field after sending
                            }
                        },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.arrow_circle_color))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_forward),
                            contentDescription = "Envoyer"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssistantUIPreview() {
    ISENSmartCompanionTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF8F3FF)
        ) {
            AssistantUI()
        }
    }
}
