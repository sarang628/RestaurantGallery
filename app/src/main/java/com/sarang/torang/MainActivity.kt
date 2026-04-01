package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.sarang.torang.compose.ImageRow
import com.sarang.torang.compose.RestaurantGalleryScreen
import com.sarang.torang.compose.type.LocalRestaurantGalleryImageLoader
import com.sarang.torang.di.restaurant_gallery_di.restaurantGalleryImageLoader
import com.sarang.torang.repository.FindRepository
import com.sarang.torang.repository.test.feed.Menu
import com.sarang.torang.ui.theme.RestaurantGalleryTheme
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var findRepository: FindRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TorangTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(Modifier.padding(it)){
                        CompositionLocalProvider(
                            LocalRestaurantGalleryImageLoader provides restaurantGalleryImageLoader
                        ) {
                            TestContainer(
                                findRepository = findRepository,
                                content = {id, restaurantName ->
                                    RestaurantGalleryNavigation(
                                        restaurantGalleryScreen = { RestaurantGalleryScreen(restaurantId = id) },
                                        imageRow = { ImageRow() }
                                    )
                                },
                                onRestaurant = {}
                            )
                        }
                    }
                }
            }
        }
    }
}