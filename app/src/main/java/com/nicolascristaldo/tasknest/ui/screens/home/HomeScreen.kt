package com.nicolascristaldo.tasknest.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.ui.components.AppTextField
import com.nicolascristaldo.tasknest.ui.components.TaskList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToTaskDetails: (Int) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AppTextField(
            value = uiState.nameFilter ?: "",
            onValueChange = { viewModel.setNameFilter(it) },
            label = stringResource(R.string.search),
            modifier = Modifier.fillMaxWidth(.8f)
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.extra_small_padding)))

        when {
            uiState.isLoading -> Text(text = stringResource(R.string.loading))
            uiState.error != null -> Text(text = uiState.error)
            else -> TaskList(
                tasks = uiState.tasks,
                onCardClick = onNavigateToTaskDetails,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}