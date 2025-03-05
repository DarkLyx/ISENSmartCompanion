package fr.isen.lucas.isensmartcompanion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fr.isen.lucas.isensmartcompanion.models.Event
import fr.isen.lucas.isensmartcompanion.screens.EventDetails

class EventDetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("EventDetailsActivity", "onCreate appelé")

        val event = intent.getParcelableExtra<Event>("event")

        setContent {
            if (event != null) {
                EventDetails(event)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("EventDetailsActivity", "onStart appelé")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EventDetailsActivity", "onResume appelé")
    }

    override fun onPause() {
        super.onPause()
        Log.d("EventDetailsActivity", "onPause appelé")
    }

    override fun onStop() {
        super.onStop()
        Log.d("EventDetailsActivity", "onStop appelé")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("EventDetailsActivity", "onRestart appelé")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EventDetailsActivity", "onDestroy appelé")
    }
}
