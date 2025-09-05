package com.nicolascristaldo.tasknest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Status

@Composable
fun StatusWithIcon(
    status: Status,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        val icon = when (status) {
            Status.PENDING -> R.drawable.ic_pending
            Status.IN_PROGRESS -> R.drawable.ic_in_progress
            Status.COMPLETED -> R.drawable.ic_completed
        }

        Text(
            text = status.name.replace('_', ' '),
            style = MaterialTheme.typography.labelLarge
        )

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(16.dp)
        )
    }
}