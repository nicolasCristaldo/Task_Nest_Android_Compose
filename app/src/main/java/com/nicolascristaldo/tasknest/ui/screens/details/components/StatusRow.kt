package com.nicolascristaldo.tasknest.ui.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Status

@Composable
fun StatusRow(
    modifier: Modifier = Modifier,
    status: Status,
    onStatusChange: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.status, status.name.replace('_', ' ')))

        Spacer(modifier = Modifier.padding(16.dp))

        OutlinedButton(
            onClick = { onStatusChange() }
        ) {
            Text(
                text = stringResource(R.string.change),
                fontWeight = FontWeight.Bold
            )
        }
    }
}