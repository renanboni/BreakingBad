package com.boni.breakingbadfacts.features.episodes

import com.boni.breakingbadfacts.base.BaseViewModel
import com.boni.breakingbadfacts.base.ViewState
import com.boni.breakingbadfacts.base.onError
import com.boni.breakingbadfacts.base.onSuccess
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.features.model.Episode
import com.boni.breakingbadfacts.utils.toEpisodes

class EpisodesViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val episodesState = intoMediator<EpisodesViewState.Episodes>()

    fun getEpisodes(season: Int) {
        load {
            repository.getEpisodes()
                .onSuccess {
                    val episodes = it.toEpisodes().filter { it.episode.toInt() == season }
                    episodesState.postValue(EpisodesViewState.Episodes(episodes))
                }
                .onError { }
        }
    }

    sealed class EpisodesViewState : ViewState {
        data class Episodes(val episodes: List<Episode>) : EpisodesViewState()
    }
}