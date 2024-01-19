package com.thuhtooaung.punkbeers.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thuhtooaung.punkbeers.data.model.Beer

@Composable
fun BeerItem(
    modifier: Modifier = Modifier,
    item: Beer
) {
    Card(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = item.imageUrl,
            contentDescription = item.name
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = item.name, style = MaterialTheme.typography.titleSmall)
            Text(
                text = item.tagline,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}