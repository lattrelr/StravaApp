package com.ryanl.stravasramapp.api.models

data class TokenRequest(
    val client_id: String,
    val client_secret: String,
    val code: String,
    val grant_type: String
)
