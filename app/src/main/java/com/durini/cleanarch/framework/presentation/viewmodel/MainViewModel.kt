package com.durini.cleanarch.framework.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.durini.cleanarch.business.domain.state.DataState
import com.durini.cleanarch.business.interactors.GetHeroes
import com.durini.cleanarch.framework.presentation.event.MainEvents
import com.durini.cleanarch.framework.presentation.state.HeroListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val getHeroes: GetHeroes
) : ViewModel() {

    private val _uiState: MutableStateFlow<HeroListState> = MutableStateFlow(HeroListState.LoadingHeroes())
    val uiState: StateFlow<HeroListState> = _uiState

    fun onTriggerEvent(event: MainEvents) {
        when(event) {
            is MainEvents.GetHeroes -> {
                getHeroes()
            }
        }
    }

    private fun getHeroes() {
        getHeroes.execute().onEach { dataState ->
            when(dataState) {
                is DataState.Response -> {
                    println("THE_CLEAN: Response")
                }

                is DataState.Data -> {
                    _uiState.value = HeroListState.SuccessHeroes(heroes = dataState.data ?: listOf())
                }

                is DataState.Loading -> {
                    _uiState.value = HeroListState.LoadingHeroes(loadingState = dataState.loadingState)
                }
            }
        }.launchIn(viewModelScope)
    }

}