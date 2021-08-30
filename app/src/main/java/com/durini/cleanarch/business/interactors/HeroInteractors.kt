package com.durini.cleanarch.business.interactors

import com.durini.cleanarch.framework.datasource.service.HeroService
import retrofit2.Retrofit

data class HeroInteractors(
    val getHeroes: GetHeroes
) {
    companion object Factory {
        fun build(retrofit: Retrofit) : HeroInteractors {
            val heroService = HeroService.build(retrofit)
            val getHeroes = GetHeroes(heroService)
            return HeroInteractors(
                getHeroes = getHeroes
            )
        }
    }
}