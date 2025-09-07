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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Status

@Composable
fun StatusWithIcon(
    status: Status,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    iconSize: Int = R.dimen.small_icon_size,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
        modifier = modifier
    ) {
        Text(
            text = status.name.replace('_', ' '),
            style = textStyle
        )

        Icon(
            painter = painterResource(id = status.icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(dimensionResource(iconSize))
        )
    }
}