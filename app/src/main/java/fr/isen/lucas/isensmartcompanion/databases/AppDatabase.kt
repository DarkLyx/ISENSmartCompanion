package fr.isen.lucas.isensmartcompanion.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.isen.lucas.isensmartcompanion.models.Conversation

@Database(entities = [Conversation::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao

}
