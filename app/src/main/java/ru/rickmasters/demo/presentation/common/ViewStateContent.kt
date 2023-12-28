package ru.rickmasters.demo.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.rickmasters.demo.R
import ru.rickmasters.demo.domain.model.ViewState

@Composable
fun <T> ViewStateContent(
    state: ViewState<T>,
    onError: @Composable (String?) -> Unit = { ErrorPage(errorMessage = it ?: stringResource(R.string.error)) },
    content: @Composable (T) -> Unit
) {
    when (state) {
        is ViewState.Loading -> {
            LoadingPage()
        }
        is ViewState.Success -> {
            content(state.result)
        }
        is ViewState.Error -> {
            onError(state.errorMessage)
        }
    }
}

@Composable
fun LoadingPage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
           color = Color.Gray
        )
    }
}

@Composable
fun ErrorPage(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}