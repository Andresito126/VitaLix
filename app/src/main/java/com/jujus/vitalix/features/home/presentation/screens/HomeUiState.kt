package com.jujus.vitalix.features.home.presentation.screens

data class HomeUiState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)