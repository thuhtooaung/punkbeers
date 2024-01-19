package com.thuhtooaung.punkbeers.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.thuhtooaung.punkbeers.data.local.BeerEntity
import com.thuhtooaung.punkbeers.data.mapper.toBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    beerPager: Pager<Int, BeerEntity>
) : ViewModel() {

    val beers = beerPager.flow.map { pagingData ->
        pagingData.map { it.toBeer() }
    }.cachedIn(viewModelScope)

}