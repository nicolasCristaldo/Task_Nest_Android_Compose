package com.nicolascristaldo.tasknest.ui.screens.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.ui.components.StatusWithIcon

@Composable
fun ChangeStatusCard(
    status: Status,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = dimensionResource(R.dimen.small_border_width),
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Text(
            text = stringResource(R.string.status),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(
                bottom = dimensionResource(R.dimen.small_padding),
                top = dimensionResource(R.dimen.medium_padding)
            )
        )

        StatusWithIcon(
            status = status,
            textStyle = MaterialTheme.typography.titleMedium,
            iconSize = R.dimen.large_icon_size,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium_padding))
        )

        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium_padding))
        ) {
            Text(
                text = stringResource(R.string.change),
                fontWeight = FontWeight.Bold
            )
        }
    }
}