package com.nicolascristaldo.tasknest.ui.screens.form.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.tasknest.R

@Composable
fun NotificationSwitchSection(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.enable_notifications),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )

        VerticalDivider(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.height(dimensionResource(R.dimen.vertical_divider_height))
        )

        Switch(
            checked = checked,
            onCheckedChange = { onCheckedChange() },
            modifier = Modifier.padding(start = dimensionResource(R.dimen.medium_padding))
        )
    }
}