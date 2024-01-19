package com.thuhtooaung.punkbeers.repo.network

import com.thuhtooaung.punkbeers.data.remote.BeerDto

abstract class ApiHelper {

    abstract suspend fun getBeers(page: Int, size: Int): List<BeerDto>

}