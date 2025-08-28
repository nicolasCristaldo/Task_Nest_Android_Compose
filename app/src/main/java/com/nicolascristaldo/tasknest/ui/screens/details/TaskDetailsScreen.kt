package com.nicolascristaldo.tasknest.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TaskDetailsScreen(
    id: Int,
    viewModel: DetailsViewModel = hiltViewModel(),
    onNavigateToTaskForm: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(id) {
        viewModel.getTask(id)
    }

    val task by viewModel.task.collectAsStateWithLifecycle()

    if (task != null) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            Text(text = "name: ${task?.name ?: ""}")
            Text(text = "description: ${task?.description ?: ""}")
            Text(text = "isNotificationEnabled: ${task?.isNotificationEnabled ?: ""}")
            Text(text = "category: ${task?.category ?: ""}")
            Text(text = "status: ${task?.status ?: ""}")

            Button(
                onClick = {
                    viewModel.deleteTask()
                    onNavigateBack()
                }
            ) {
                Text(text = "Delete")
            }

            Button(
                onClick = { onNavigateToTaskForm() }
            ) {
                Text(text = "Edit")
            }
        }
    }
}