package xyz.teamgravity.compose_show_snackbar_globally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.teamgravity.compose_show_snackbar_globally.ui.theme.ComposeshowsnackbargloballyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeshowsnackbargloballyTheme {
                val snackbar = remember { SnackbarHostState() }
                val controller = rememberNavController()

                ObserveEvent(
                    flow = SnackbarController.event,
                    onEvent = { event ->
                        snackbar.currentSnackbarData?.dismiss()

                        val result = snackbar.showSnackbar(
                            message = event.message,
                            actionLabel = event.action?.label,
                            duration = SnackbarDuration.Long
                        )
                        if (result == SnackbarResult.ActionPerformed) event.action?.action?.invoke()
                    }
                )

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(snackbar)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    NavHost(
                        navController = controller,
                        startDestination = Route.ScreenA,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable<Route.ScreenA> {
                            ScreenA(
                                onNavigate = {
                                    controller.navigate(Route.ScreenB)
                                }
                            )
                        }
                        composable<Route.ScreenB> {
                            ScreenB()
                        }
                    }
                }
            }
        }
    }
}