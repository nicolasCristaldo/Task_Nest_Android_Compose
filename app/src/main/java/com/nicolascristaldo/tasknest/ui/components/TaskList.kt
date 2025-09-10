package com.nicolascristaldo.tasknest.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Task

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
                Text(text = stringResource(R.string.no_tasks_found))
            }
        }
        else {
            items(tasks, key = { it.id }) { task ->
                TaskCard(
                    task = task,
                    onClick = { onCardClick(task.id) },
                    modifier = Modifier
                        .padding(vertical = dimensionResource(R.dimen.small_padding))
                        .fillMaxWidth()
                )
            }
        }
    }
}