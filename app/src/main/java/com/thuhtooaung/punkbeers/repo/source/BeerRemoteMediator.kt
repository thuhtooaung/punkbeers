package com.thuhtooaung.punkbeers.repo.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.thuhtooaung.punkbeers.data.local.BeerEntity
import com.thuhtooaung.punkbeers.data.mapper.toBeerEntity
import com.thuhtooaung.punkbeers.repo.database.BeerDatabase
import com.thuhtooaung.punkbeers.repo.network.ApiHelper
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val apiHelper: ApiHelper,
    private val beerDatabase: BeerDatabase
) : RemoteMediator<Int, BeerEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        if (lastItem.id % state.config.pageSize == 0) {
                            (lastItem.id / state.config.pageSize) + 1
                        } else {
                            (lastItem.id / state.config.pageSize) + 2
                        }
                    }
                }
            }
            val beers = apiHelper.getBeers(page = loadKey, size = state.config.pageSize)

            beerDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDatabase.beerDao().clearAll()
                }
                val beerEntities = beers.map { it.toBeerEntity() }
                beerDatabase.beerDao().upsertAll(beerEntities)
            }

            MediatorResult.Success(endOfPaginationReached = beers.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}