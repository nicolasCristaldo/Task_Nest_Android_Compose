package com.nicolascristaldo.tasknest.ui.navigation.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun TaskNestFAB(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: Int,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(contentDescription)
        )
    }
}