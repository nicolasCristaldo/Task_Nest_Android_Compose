package com.nicolascristaldo.tasknest.ui.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.ui.navigation.components.TaskNestFAB

@Composable
fun DetailsButtons(
    onNavigateToTaskForm: () -> Unit,
    onDeleteTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TaskNestFAB(
            onClick = onDeleteTaskClick,
            icon = Icons.Filled.Delete,
            contentDescription = R.string.delete_task,
            modifier = Modifier.size(40.dp)
        )

        TaskNestFAB(
            onClick = onNavigateToTaskForm,
            icon = Icons.Filled.Edit,
            contentDescription = R.string.edit
        )
    }
}