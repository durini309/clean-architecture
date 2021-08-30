package com.durini.cleanarch.framework.datasource.service

import com.durini.cleanarch.business.domain.model.Hero
import com.durini.cleanarch.framework.datasource.api.HeroApi
import com.durini.cleanarch.framework.datasource.model.toHero

class HeroServiceImplementation(
    private val heroApi: HeroApi
) : HeroService {
    override suspend fun getHeroStats(): List<Hero> {
        return heroApi.getHeroList().map { it.toHero() }
    }
}