package com.durini.cleanarch.framework.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.durini.cleanarch.R
import com.durini.cleanarch.business.domain.state.DataState
import com.durini.cleanarch.business.interactors.HeroInteractors
import com.durini.cleanarch.framework.datasource.service.HeroService
import com.durini.cleanarch.framework.presentation.state.HeroListState
import com.durini.cleanarch.framework.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}