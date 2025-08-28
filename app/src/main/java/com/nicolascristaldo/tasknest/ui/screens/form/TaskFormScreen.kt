package com.nicolascristaldo.tasknest.ui.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascristaldo.tasknest.R

@Composable
fun TaskFormScreen(
    id: Int,
    viewModel: FormViewmodel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(id) {
        if (id != 0) viewModel.getTask(id)
    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = uiState.value.taskDetails.name,
            onValueChange = { viewModel.updateUiState(uiState.value.taskDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.name)) }
        )

        OutlinedTextField(
            value = uiState.value.taskDetails.description,
            onValueChange = { viewModel.updateUiState(uiState.value.taskDetails.copy(description = it)) },
            label = { Text(stringResource(R.string.description)) }
        )



        Switch(
           checked = uiState.value.taskDetails.isNotificationEnabled,
            onCheckedChange = {
                viewModel.updateUiState(
                    uiState.value.taskDetails.copy(
                        isNotificationEnabled = !uiState.value.taskDetails.isNotificationEnabled
                    )
                )
            }
        )

        Button(
            onClick = {
                if (id == 0) viewModel.saveTask()
                else viewModel.updateTask()
                onNavigateBack()
            },
            enabled = uiState.value.isEntryValid()
        ) {
            Text(
                stringResource(if (id == 0) R.string.create else R.string.edit)
            )
        }

        if (uiState.value.error != null) {
            Text(text = uiState.value.error!!)
        }
    }
}