package com.nicolascristaldo.tasknest.ui.screens.form.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.tasknest.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun DatePickerTextField(
    label: String,
    value: Long?,
    onClick: () -> Unit,
    isError: Boolean = false,
    errorMessage: String = "",
    modifier: Modifier = Modifier
) {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val displayValue = value?.let { formatter.format(Date(it)) } ?: "dd/mm/yyyy"

    TextField(
        value = displayValue,
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = stringResource(R.string.select_date)
                )
            }
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = "* $errorMessage",
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        },
        modifier = modifier
    )
}