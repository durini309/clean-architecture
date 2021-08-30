package com.durini.cleanarch.framework.datasource.api

import com.durini.cleanarch.framework.datasource.model.HeroDto
import retrofit2.http.GET

interface HeroApi {
    @GET("/api/heroStats/")
    suspend fun getHeroList(): List<HeroDto>
}