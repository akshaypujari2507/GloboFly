package com.akshay.globofly.services

import retrofit2.Call
import com.akshay.globofly.models.Destination
import retrofit2.http.GET

interface DestinationService {

    @GET("destination")
    fun getDestinationList(): Call<List<Destination>>

}