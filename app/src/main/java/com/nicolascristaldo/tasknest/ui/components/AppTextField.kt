package com.nicolascristaldo.tasknest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.tasknest.R

@Composable
fun AppTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    limit: Int? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    var isFirstTime by remember { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                if (isFirstTime) isFirstTime = false
            },
            singleLine = true,
            isError = if (isFirstTime) false else isError,
            supportingText = {
                if (limit != null) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (isError && !isFirstTime && errorMessage != null) {
                            Text(
                                text = "* $errorMessage",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier
                                    .wrapContentWidth(Alignment.Start)
                                    .align(Alignment.TopStart)
                                    .fillMaxWidth(.7f)
                            )
                        }
                        Text(
                            text = stringResource(R.string.limit_characters, value.length, limit),
                            modifier = Modifier.align(Alignment.TopEnd)
                        )
                    }
                }
            },
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.extra_small_padding))
                .fillMaxWidth()
        )
    }
}