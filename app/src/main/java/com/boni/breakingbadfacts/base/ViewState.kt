package com.boni.breakingbadfacts.base

interface ViewState

sealed class LoadingState : ViewState {
    object Show : LoadingState()
    object Hide : LoadingState()
}

open class ErrorState(val message: String? = "") : ViewState