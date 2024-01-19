package com.thuhtooaung.punkbeers.repo.network

import com.thuhtooaung.punkbeers.data.remote.BeerDto
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper() {

    override suspend fun getBeers(page: Int, size: Int): List<BeerDto> {
        return apiService.getBeers(page, size)
    }

}