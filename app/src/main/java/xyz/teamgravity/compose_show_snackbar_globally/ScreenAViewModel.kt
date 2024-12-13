package xyz.teamgravity.compose_show_snackbar_globally

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ScreenAViewModel : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onShowSnackbarWithAction() {
        viewModelScope.launch {
            SnackbarController.show(
                event = SnackbarController.SnackbarEvent(
                    message = "Hello From ViewModel",
                    action = SnackbarController.SnackbarAction(
                        label = "Click me!",
                        action = {
                            SnackbarController.show(
                                event = SnackbarController.SnackbarEvent(
                                    message = "Action pressed!"
                                )
                            )
                        }
                    )
                )
            )
        }
    }
}