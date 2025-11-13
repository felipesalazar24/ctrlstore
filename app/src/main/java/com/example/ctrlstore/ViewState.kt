package com.example.ctrlstore.utils

sealed class ViewState {
    object Loading : ViewState()
    object Success : ViewState()
    data class Error(val message: String) : ViewState()
    object Idle : ViewState()
}