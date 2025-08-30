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
import androidx.compose.ui.unit.dp

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
            text = "Enable notifications",
            modifier = Modifier.weight(1f)
        )

        VerticalDivider(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.height(24.dp)
        )

        Switch(
            checked = checked,
            onCheckedChange = { onCheckedChange() },
            modifier = Modifier.padding(start = 16.dp)
        )

    }
}