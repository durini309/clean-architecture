package com.durini.cleanarch.business.interactors

import com.durini.cleanarch.business.domain.model.Hero
import com.durini.cleanarch.business.domain.state.DataState
import com.durini.cleanarch.business.domain.state.LoadingState
import com.durini.cleanarch.business.domain.state.UIComponent
import com.durini.cleanarch.framework.datasource.service.HeroService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import kotlin.random.Random

class GetHeroes(
    private val service: HeroService
) {
    fun execute(): Flow<DataState<List<Hero>>> = flow {
        try {
            emit(DataState.Loading(loadingState = LoadingState.Loading))
            kotlinx.coroutines.delay(2000)
            val heroes: List<Hero> = service.getHeroStats()
            emit(DataState.Data(heroes))
        } catch (e: Exception) {
            emit(
                DataState.Response<List<Hero>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(loadingState = LoadingState.Idle))
        }
    }
}