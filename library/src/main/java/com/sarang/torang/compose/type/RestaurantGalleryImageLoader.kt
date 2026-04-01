package com.sarang.torang.compose.type

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

typealias RestaurantGalleryImageLoader = @Composable (
    RestaurantGalleryImageLoaderData
) -> Unit

data class RestaurantGalleryImageLoaderData(
    val modifier: Modifier = Modifier,
    val url: String = "",
    val width: Dp? = 30.dp,
    val height: Dp? = 30.dp,
    val contentScale: ContentScale? = ContentScale.Fit
)

val LocalRestaurantGalleryImageLoader = compositionLocalOf<RestaurantGalleryImageLoader> {
    // 기본 구현: 경고 로그 출력
    @Composable {
        Log.w("ImageLoader", "No ImageLoader provided.")
        Box(modifier = it.modifier.background(color = androidx.compose.ui.graphics.Color.LightGray))
    }
}