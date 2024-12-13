package xyz.teamgravity.compose_show_snackbar_globally

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object ScreenA : Route

    @Serializable
    data object ScreenB : Route
}