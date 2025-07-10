package com.sarang.torang.usecase

import com.sarang.torang.data.RestaurantImage

interface GetRestaurantGalleryUseCase {
    suspend fun invoke(restaurantId : Int): List<RestaurantImage>
}