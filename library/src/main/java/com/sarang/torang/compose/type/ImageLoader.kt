package com.sarang.torang.compose.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.sarang.torang.compose.type.LocalRestaurantGalleryImageLoader
import com.sarang.torang.compose.type.RestaurantGalleryImageLoaderData

@Composable
fun ImageLoader(modifier : Modifier,
                url : String,
                contentScale: ContentScale) {
    LocalRestaurantGalleryImageLoader.current.invoke(
        RestaurantGalleryImageLoaderData(
            modifier = modifier,
            url = url,
            contentScale = contentScale
        )
    )
}