package com.nicolascristaldo.tasknest.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.ui.screens.details.components.DeleteTaskDialog
import com.nicolascristaldo.tasknest.ui.screens.details.components.DetailsButtons
import com.nicolascristaldo.tasknest.ui.screens.details.components.StatusRow

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
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (task != null) {
        TaskDetailsBody(
            task = task!!,
            onNavigateToTaskForm = onNavigateToTaskForm,
            onDeleteTaskClick = { showDeleteDialog = true },
            onStatusChange = { viewModel.changeStatus() },
            modifier = modifier
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text(text = stringResource(R.string.task_not_found))
        }
    }

    if (showDeleteDialog) {
        DeleteTaskDialog(
            onDismiss = { showDeleteDialog = false },
            onConfirm = {
                viewModel.deleteTask()
                onNavigateBack()
            }
        )
    }
}

@Composable
fun TaskDetailsBody(
    task: Task,
    onNavigateToTaskForm: () -> Unit,
    onStatusChange: () -> Unit,
    onDeleteTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        Text(text = "Category: ${task.category.name}")

        Text(text = task.name)

        task.description?.let {
            Spacer(modifier = Modifier.padding(16.dp))

            Text(text = it)
        }

        task.date?.let {
            Spacer(modifier = Modifier.padding(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null
                )
                Text(
                    text = task.date.toString()
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        HorizontalDivider(color = Color(task.category.color))

        Spacer(modifier = Modifier.padding(16.dp))

        StatusRow(
            status = task.status,
            onStatusChange = onStatusChange,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(16.dp))

        DetailsButtons(
            onNavigateToTaskForm = onNavigateToTaskForm,
            onDeleteTaskClick = onDeleteTaskClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}