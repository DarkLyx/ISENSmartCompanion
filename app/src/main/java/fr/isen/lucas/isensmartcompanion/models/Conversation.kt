package fr.isen.lucas.isensmartcompanion.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Conversation(
    @PrimaryKey(autoGenerate = true) val titleid: Long = 0L,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "answer") var answer: String
)
