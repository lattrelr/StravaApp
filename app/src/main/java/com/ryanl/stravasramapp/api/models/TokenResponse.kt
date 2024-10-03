package com.ryanl.stravasramapp.api.models

data class TokenResponse(
    val expires_at: Long,
    val expires_in: Long,
    val refresh_token: String,
    val token_type: String,
    val access_token: String
)
