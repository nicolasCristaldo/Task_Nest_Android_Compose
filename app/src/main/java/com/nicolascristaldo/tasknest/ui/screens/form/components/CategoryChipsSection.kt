package com.nicolascristaldo.tasknest.ui.screens.form.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.tasknest.R
import com.nicolascristaldo.tasknest.domain.model.Category

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryChipsSection(
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.select_category),
            style = MaterialTheme.typography.titleMedium
        )

        FlowRow(
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 3,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Category.entries.forEach { category ->
                FilterChip(
                    label = { Text(text = category.name) },
                    selected = category == selectedCategory,
                    onClick = { onCategorySelected(category) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedLabelColor = Color(category.color)
                    ),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}