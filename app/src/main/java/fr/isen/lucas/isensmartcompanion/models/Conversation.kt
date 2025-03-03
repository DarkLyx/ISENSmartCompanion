package fr.isen.lucas.isensmartcompanion.models

import java.util.Date

data class Conversation (
    val title : String,
    val date: Date,
    val question: String,
    var answer: String
)