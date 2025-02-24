package com.example.composeapp.ui.shelf

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.diolkaee.alexandria.R
import com.example.composeapp.ui.common.AlexandriaPreview
import com.example.composeapp.ui.theme.AlexandriaTheme

@Composable
fun ShelfActionBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onActionButtonClick: () -> Unit,
    onScanButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        SearchBar(query, onQueryChange, Modifier.weight(1f))
        Spacer(Modifier.width(16.dp))
        IconButton(modifier = Modifier.padding(4.dp), onClick = onScanButtonClick) {
            Icon(painterResource(R.drawable.ic_camera), contentDescription = "Open book scanner")
        }
        Spacer(Modifier.width(16.dp))
        IconButton(modifier = Modifier.padding(4.dp), onClick = onActionButtonClick) {
            Icon(Icons.Default.Build, contentDescription = "Change layout")
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier,
        value = query,
        label = {
            Text(
                text = stringResource(R.string.shelf_search_label),
                style = MaterialTheme.typography.labelLarge,
            )
        },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        onValueChange = { onQueryChange(it.trim()) },
    )
}

@AlexandriaPreview
@Composable
private fun Preview() {
    AlexandriaTheme {
        ShelfActionBar(
            query = "",
            onQueryChange = {},
            onActionButtonClick = {},
            onScanButtonClick = {},
        )
    }
}
