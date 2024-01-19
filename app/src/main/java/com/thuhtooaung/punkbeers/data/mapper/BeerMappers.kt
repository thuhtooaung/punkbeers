package com.thuhtooaung.punkbeers.data.mapper

import com.thuhtooaung.punkbeers.data.local.BeerEntity
import com.thuhtooaung.punkbeers.data.model.Beer
import com.thuhtooaung.punkbeers.data.remote.BeerDto

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(id, name, tagline, description, imageUrl)
}