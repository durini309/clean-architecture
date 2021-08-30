package com.durini.cleanarch.framework.presentation.state

import com.durini.cleanarch.business.domain.model.Hero
import com.durini.cleanarch.business.domain.state.LoadingState

sealed class HeroListState {
    data class SuccessHeroes(
        val heroes: List<Hero> = listOf()
    ) : HeroListState()

    data class LoadingHeroes(
        val loadingState: LoadingState = LoadingState.Idle
    ) : HeroListState()
}