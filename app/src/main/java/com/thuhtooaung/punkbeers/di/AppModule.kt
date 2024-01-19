package com.thuhtooaung.punkbeers.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.thuhtooaung.punkbeers.data.local.BeerEntity
import com.thuhtooaung.punkbeers.repo.database.BeerDatabase
import com.thuhtooaung.punkbeers.repo.network.ApiHelper
import com.thuhtooaung.punkbeers.repo.source.BeerRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(
        apiHelper: ApiHelper,
        beerDatabase: BeerDatabase
    ): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                apiHelper = apiHelper,
                beerDatabase = beerDatabase
            ),
            pagingSourceFactory = {
                beerDatabase.beerDao().getAllBeer()
            }
        )
    }

}