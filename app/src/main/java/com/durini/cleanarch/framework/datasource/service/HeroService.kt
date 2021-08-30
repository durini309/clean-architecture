package com.durini.cleanarch.framework.datasource.service

import com.durini.cleanarch.business.domain.model.Hero
import com.durini.cleanarch.framework.datasource.api.HeroApi
import retrofit2.Retrofit

interface HeroService {

    suspend fun getHeroStats(): List<Hero>

    // Add Hero, Remove Hero, Update Hero ...

    companion object Factory {
        fun build(retrofit: Retrofit) : HeroService {
            return HeroServiceImplementation(
                heroApi = retrofit.create(HeroApi::class.java)
            )
        }
    }
}