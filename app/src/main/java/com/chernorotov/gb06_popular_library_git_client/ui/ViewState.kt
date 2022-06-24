package com.chernorotov.gb06_popular_library_git_client.ui


sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val error: Throwable) : ViewState<Nothing>()
}