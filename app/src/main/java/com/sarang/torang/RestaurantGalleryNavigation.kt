package com.sarang.torang

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RestaurantGalleryNavigation(
    restaurantGalleryScreen : @Composable () -> Unit = {},
    imageRow : @Composable () -> Unit = {},
){
    val navController = rememberNavController()
    NavHost(navController = navController, "menu"){
        composable("menu"){
            Column {
                Button({
                    navController.navigate("RestaurantGalleryScreen")
                }) { Text("RestaurantGalleryScreen") }
                Button({
                    navController.navigate("ImageRow")
                }) { Text("ImageRow") }
            }
        }
        composable("RestaurantGalleryScreen") {
            restaurantGalleryScreen()
        }

        composable("ImageRow"){
            imageRow()
        }
    }
}