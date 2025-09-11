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
    LaunchedEffect(id) { if (id != 0) viewModel.getTask(id) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showDatePicker by remember { mutableStateOf(false) }
    val confirmButtonTextId = remember(id) { if (id == 0) R.string.create else R.string.edit }

    TaskFormBody(
        confirmButtonTextId = confirmButtonTextId,
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
    confirmButtonTextId: Int,
    onConfirm: () -> Unit,
    uiState: FormUiState,
    updateUiState: (TaskDetails) -> Unit,
    changeDatePickerState: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val taskDetails = uiState.taskDetails

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        item {
            AppTextField(
                label = stringResource(R.string.name),
                value = taskDetails.name,
                onValueChange = { updateUiState(taskDetails.copy(name = it)) },
                limit = 30,
                isError = !uiState.isNameValid(),
                errorMessage = stringResource(R.string.name_error),
                modifier = Modifier.fillMaxWidth(.8f)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_padding)))

            AppTextField(
                label = stringResource(R.string.description),
                value = taskDetails.description,
                onValueChange = { updateUiState(taskDetails.copy(description = it)) },
                limit = 200,
                isError = !uiState.isDescriptionValid(),
                errorMessage = stringResource(R.string.description_error),
                modifier = Modifier.fillMaxWidth(.8f)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_padding)))

            DatePickerTextField(
                label = stringResource(R.string.date),
                value = taskDetails.date,
                isError = !uiState.isDateValid(),
                errorMessage = stringResource(R.string.date_error),
                onClick = { changeDatePickerState(true) },
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .clickable { changeDatePickerState(true) }
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_padding)))

            NotificationSwitchSection(
                checked = taskDetails.isNotificationEnabled,
                enabled = uiState.isDateValid() && taskDetails.date != null,
                onCheckedChange = { updateUiState(taskDetails.copy(isNotificationEnabled = it)) },
                modifier = Modifier.fillMaxWidth(.8f)
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_padding)))

            CategoryChipsSection(
                selectedCategory = taskDetails.category,
                onCategorySelected = { updateUiState(taskDetails.copy(category = it)) }
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.extra_large_padding)))

            Button(
                onClick = { onConfirm() },
                enabled = uiState.isEntryValid()
            ) {
                Text(
                    text = stringResource(confirmButtonTextId),
                    fontWeight = FontWeight.Bold
                )
            }

            if (uiState.error != null) {
                Text(text = uiState.error)
            }
        }
    }
}