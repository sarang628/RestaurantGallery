package com.sarang.torang.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.type.LocalRestaurantGalleryImageLoader
import com.sarang.torang.compose.type.RestaurantGalleryImageLoaderData

@Composable
fun ImageRow(galleryImages: GalleryImages,
             onImage : (Int) -> Unit = {}){
    Row(Modifier.fillMaxWidth()) {
        galleryImages.images.forEach {
            LocalRestaurantGalleryImageLoader.current.invoke(
                RestaurantGalleryImageLoaderData(
                    Modifier.padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .height(120.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            onImage.invoke(it.first)
                        },
                    url = it.second.replaceM3u8WithJpg(),
                    contentScale = ContentScale.Crop
                )
            )
        }
    }
}

data class GalleryImages(
    val images : List<Pair<Int, String>>
)

fun String.replaceM3u8WithJpg(): String {
    return if (this.endsWith(".m3u8", ignoreCase = true)) {
        this.replace(Regex("\\.m3u8$", RegexOption.IGNORE_CASE), ".jpg")
    } else {
        this
    }
}

fun RestaurantGalleryViewModel.galleryImages() : List<GalleryImages>{
    return this.uiState.chunked(3){chunk ->
        GalleryImages(
            images = chunk.map { it.id to it.url },
        )
    }
}