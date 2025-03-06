package fr.isen.lucas.isensmartcompanion.services


import fr.isen.lucas.isensmartcompanion.models.Event
import retrofit2.Response
import retrofit2.http.GET

interface EventApiService {
    @GET("events.json")
    suspend fun getEvents(): Response<List<Event>>
}