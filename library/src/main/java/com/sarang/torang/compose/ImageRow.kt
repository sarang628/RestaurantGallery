package com.sarang.torang.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.type.ImageLoader

@Composable
fun ImageRow(galleryImages: GalleryImages = GalleryImages(emptyList()),
             onImage : (Int) -> Unit = {}){
    Row(Modifier.fillMaxWidth()) {
        galleryImages.images.forEachIndexed { index, pair ->
            ImageLoader(modifier = Modifier.padding(bottom = 8.dp)
                                            .fillMaxWidth()
                                            .height(120.dp)
                                            .weight(1f)
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                onImage.invoke(pair.first)
                                            },
                        url = pair.second.replaceM3u8WithJpg(),
                        contentScale = ContentScale.Crop)

            //마지막 항목 패딩 안주기
            if(galleryImages.images.size-1 > index) Spacer(Modifier.width(8.dp))
        }
    }
}

@Preview
@Composable
fun PreviewImageRow(){
    ImageRow(
        galleryImages = GalleryImages(listOf(
            Pair(0, ""),
            Pair(0, ""),
            Pair(0, ""),
        ))
    )
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