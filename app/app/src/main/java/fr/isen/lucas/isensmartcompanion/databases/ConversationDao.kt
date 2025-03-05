package fr.isen.lucas.isensmartcompanion.databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import fr.isen.lucas.isensmartcompanion.models.Conversation


@Dao
interface ConversationDao {
    @Insert
    suspend fun insert(conversation: Conversation)

    @Query("SELECT * FROM conversation ORDER BY date DESC")
    suspend fun getAll(): List<Conversation>

    @Delete
    suspend fun delete(conversation: Conversation)

    @Query("DELETE FROM conversation")
    suspend fun deleteAll()
}

