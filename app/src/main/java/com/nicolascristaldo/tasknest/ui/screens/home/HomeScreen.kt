package com.nicolascristaldo.tasknest.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.ui.components.AppTextField
import com.nicolascristaldo.tasknest.ui.screens.home.components.TaskCard

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
            label = "Search",
            modifier = Modifier.fillMaxWidth(.8f)
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text(text = uiState.error)
            else -> TaskList(
                tasks = uiState.tasks,
                onCardClick = onNavigateToTaskDetails,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (tasks.isEmpty()) {
            item {
                Text(text = "No tasks found")
            }
        }
        else {
            items(tasks, key = { it.id }) { task ->
                TaskCard(
                    task = task,
                    onClick = { onCardClick(task.id) },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}