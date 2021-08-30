package com.durini.cleanarch.framework.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.durini.cleanarch.R
import com.durini.cleanarch.business.domain.state.LoadingState
import com.durini.cleanarch.databinding.FragmentHeroBinding
import com.durini.cleanarch.framework.presentation.event.MainEvents
import com.durini.cleanarch.framework.presentation.state.HeroListState
import com.durini.cleanarch.framework.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroFragment : Fragment(R.layout.fragment_hero) {

    private lateinit var binding: FragmentHeroBinding
    private val viewModel: MainViewModel by viewModels()
    private var uiStateJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        subscribeToChanges()
        viewModel.onTriggerEvent(MainEvents.GetHeroes)
//        subscribeToChangesWithJob()
    }

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    private fun setListeners() {
        binding.heroGetDataButton.setOnClickListener {
            viewModel.onTriggerEvent(MainEvents.GetHeroes)
        }
    }

    private fun handleState(state: HeroListState) {
        when(state) {
            is HeroListState.LoadingHeroes -> {
                if (state.loadingState == LoadingState.Loading) {
                    binding.heroProgressbar.visibility = View.VISIBLE
                    binding.heroGetDataButton.visibility = View.GONE
                    binding.heroTotalHeroesTxt.visibility = View.GONE
                    binding.heroTotalHeroesTxt.text = ""
                } else {
                    binding.heroProgressbar.visibility = View.GONE
                    binding.heroGetDataButton.visibility = View.VISIBLE
                    binding.heroTotalHeroesTxt.visibility = View.VISIBLE
                }
            }

            is HeroListState.SuccessHeroes -> {
                binding.heroTotalHeroesTxt.text = "${state.heroes.count()} heroes were fetched"
            }
        }
    }

    private fun subscribeToChanges() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                handleState(state)
            }
        }
    }

    private fun subscribeToChangesWithJob() {
        uiStateJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                handleState(state)
            }
        }
    }
}