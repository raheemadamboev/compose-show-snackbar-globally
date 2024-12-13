package xyz.teamgravity.compose_show_snackbar_globally

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScreenA(
    onNavigate: () -> Unit,
    scope: CoroutineScope = rememberCoroutineScope(),
    viewmodel: ScreenAViewModel = viewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                scope.launch {
                    SnackbarController.show(
                        event = SnackbarController.SnackbarEvent(
                            message = "Hello world!"
                        )
                    )
                }
            }
        ) {
            Text(
                text = stringResource(R.string.show_snackbar)
            )
        }
        Button(
            onClick = viewmodel::onShowSnackbarWithAction
        ) {
            Text(
                text = stringResource(R.string.show_snackbar_action)
            )
        }
        Button(
            onClick = onNavigate
        ) {
            Text(
                text = stringResource(R.string.navigate_to_screen_b)
            )
        }
    }
}