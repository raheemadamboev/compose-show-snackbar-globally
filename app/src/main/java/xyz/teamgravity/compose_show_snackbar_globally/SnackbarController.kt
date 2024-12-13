package xyz.teamgravity.compose_show_snackbar_globally

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

object SnackbarController {

    private val _event = Channel<SnackbarEvent>()
    val event: Flow<SnackbarEvent> = _event.receiveAsFlow()

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    suspend fun show(event: SnackbarEvent) {
        _event.send(event)
    }

    ///////////////////////////////////////////////////////////////////////////
    // MISC
    ///////////////////////////////////////////////////////////////////////////

    data class SnackbarEvent(
        val message: String,
        val action: SnackbarAction? = null
    )

    data class SnackbarAction(
        val label: String,
        val action: suspend () -> Unit
    )
}