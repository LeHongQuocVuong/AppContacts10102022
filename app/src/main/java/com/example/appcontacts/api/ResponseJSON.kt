package com.example.appcontacts.api

data class ResponseJSON(
    val info: Info,
    val results: List<Contact>
)