package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.sarang.torang.compose.RestaurantGalleryScreen
import com.sarang.torang.compose.type.LocalRestaurantGalleryImageLoader
import com.sarang.torang.di.restaurant_gallery_di.restaurantGalleryImageLoader
import com.sarang.torang.ui.theme.RestaurantGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestaurantGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CompositionLocalProvider(
                        LocalRestaurantGalleryImageLoader provides restaurantGalleryImageLoader
                    ) {
                        RestaurantGalleryScreen(restaurantId = 234)
                    }
                }
            }
        }
    }
}