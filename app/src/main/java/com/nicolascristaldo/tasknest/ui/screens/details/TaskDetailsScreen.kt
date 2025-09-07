package com.nicolascristaldo.tasknest.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.ui.components.DateWithIcon
import com.nicolascristaldo.tasknest.ui.screens.details.components.ChangeStatusCard
import com.nicolascristaldo.tasknest.ui.screens.details.components.DeleteTaskDialog
import com.nicolascristaldo.tasknest.ui.screens.details.components.DetailsButtons

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

        Text(
            text = task.category.name,
            style = MaterialTheme.typography.labelLarge,
            color = Color(task.category.color)
        )

        Text(
            text = task.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        task.description?.let {
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.small_padding)))

            Text(text = it)
        }

        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding)))

        HorizontalDivider(color = Color(task.category.color).copy(alpha = 0.5f))

        task.date?.let {
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding)))

            DateWithIcon(
                date = it,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)))

        ChangeStatusCard(
            status = task.status,
            onClick = onStatusChange,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        DetailsButtons(
            onNavigateToTaskForm = onNavigateToTaskForm,
            onDeleteTaskClick = onDeleteTaskClick,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

