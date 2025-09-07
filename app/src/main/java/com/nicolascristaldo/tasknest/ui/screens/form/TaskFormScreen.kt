package com.nicolascristaldo.tasknest.ui.screens.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.ui.components.AppTextField
import com.nicolascristaldo.tasknest.ui.screens.form.components.CategoryChipsSection
import com.nicolascristaldo.tasknest.ui.screens.form.components.DatePickerTextField
import com.nicolascristaldo.tasknest.ui.screens.form.components.NotificationSwitchSection
import com.nicolascristaldo.tasknest.ui.screens.form.components.TaskDatePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
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

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showDatePicker by remember { mutableStateOf(false) }

    TaskFormBody(
        confirmButtonText = if (id == 0) stringResource(R.string.create)
        else stringResource(R.string.edit),
        onConfirm = {
            if (id == 0) viewModel.saveTask() else viewModel.updateTask()
            onNavigateBack()
        },
        uiState = uiState,
        updateUiState = viewModel::updateUiState,
        changeDatePickerState = { showDatePicker = it },
        modifier = modifier
    )

    if (showDatePicker) {
        TaskDatePickerDialog(
            date = uiState.taskDetails.date,
            onConfirm = {
                viewModel.updateUiState(uiState.taskDetails.copy(date = it))
            },
            onDismiss = { showDatePicker = false }
        )
    }
}

@Composable
fun TaskFormBody(
    confirmButtonText: String,
    onConfirm: () -> Unit,
    uiState: FormUiState,
    updateUiState: (TaskDetails) -> Unit,
    changeDatePickerState: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        item {
            AppTextField(
                label = stringResource(R.string.name),
                value = uiState.taskDetails.name,
                onValueChange = { updateUiState(uiState.taskDetails.copy(name = it)) },
                limit = 30,
                isError = !uiState.isNameValid(),
                errorMessage = stringResource(R.string.name_error),
                modifier = Modifier.fillMaxWidth(.8f)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_padding)))

            AppTextField(
                label = stringResource(R.string.description),
                value = uiState.taskDetails.description,
                onValueChange = { updateUiState(uiState.taskDetails.copy(description = it)) },
                limit = 200,
                isError = !uiState.isDescriptionValid(),
                errorMessage = stringResource(R.string.description_error),
                modifier = Modifier.fillMaxWidth(.8f)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_padding)))

            DatePickerTextField(
                label = stringResource(R.string.date),
                value = uiState.taskDetails.date,
                isError = !uiState.isDateValid(),
                errorMessage = stringResource(R.string.date_error),
                onClick = { changeDatePickerState(true) },
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .clickable { changeDatePickerState(true) }
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_padding)))

            NotificationSwitchSection(
                checked = uiState.taskDetails.isNotificationEnabled,
                onCheckedChange = {
                    updateUiState(
                        uiState.taskDetails.copy(
                            isNotificationEnabled = !uiState.taskDetails.isNotificationEnabled
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(.8f)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_padding)))

            CategoryChipsSection(
                selectedCategory = uiState.taskDetails.category,
                onCategorySelected = { updateUiState(uiState.taskDetails.copy(category = it)) }
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.extra_large_padding)))

            Button(
                onClick = { onConfirm() },
                enabled = uiState.isEntryValid()
            ) {
                Text(
                    text = confirmButtonText,
                    fontWeight = FontWeight.Bold
                )
            }

            if (uiState.error != null) {
                Text(text = uiState.error)
            }
        }
    }
}