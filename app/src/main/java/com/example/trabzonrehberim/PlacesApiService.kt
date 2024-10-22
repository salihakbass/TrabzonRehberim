package com.example.trabzonrehberim

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {
    @GET("nearbysearch/json")
    fun getNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): Call<PlacesResponse>
}

// Response modelini oluştur
data class PlacesResponse(
    val results: List<Place>
)

data class Place(
    val name: String,
    val photos: List<Photo>?,
    val geometry: Geometry
)

data class Photo(
    val photo_reference: String
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)
