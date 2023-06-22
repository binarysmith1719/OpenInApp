package com.example.openinapp.Interfaces

import com.example.openinapp.API.ApiResponse

interface dataFetchSuccessful {
    fun onSuccess(apiResponse: ApiResponse)
}