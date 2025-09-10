package com.nicolascristaldo.tasknest.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Task

@Composable
fun TaskCard(
    task: Task,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        onClick = onClick,
        border = BorderStroke(
            width = dimensionResource(R.dimen.medium_border_width),
            color = Color(task.category.color).copy(alpha = 0.5f)
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium_padding))
                .fillMaxSize()
        ) {
            Text(
                text = task.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color(task.category.color),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.small_padding)))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatusWithIcon(status = task.status)
                if (task.date != null) {
                    DateWithIcon(
                        date = task.date,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.small_padding))
                    )
                }
            }
        }
    }
}