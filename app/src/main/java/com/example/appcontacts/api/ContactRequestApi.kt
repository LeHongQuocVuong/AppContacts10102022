package com.example.appcontacts.api

import retrofit2.Call
import retrofit2.http.GET

interface ContactRequestApi {
    @GET("api/?results=10")
    fun getContacts(): Call<ResponseJSON>
}