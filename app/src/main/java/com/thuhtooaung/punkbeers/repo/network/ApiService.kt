package com.thuhtooaung.punkbeers.repo.network

import com.thuhtooaung.punkbeers.data.remote.BeerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): List<BeerDto>

}