package com.example.bincardapp.core.models

data class TextField(
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: Int = 0,
)
