package com.boni.breakingbadfacts.features.episodes

import com.boni.breakingbadfacts.base.*
import com.boni.breakingbadfacts.data.BreakingBadRepository
import com.boni.breakingbadfacts.features.model.Episode

class EpisodesViewModel(private val repository: BreakingBadRepository) : BaseViewModel() {

    private val episodesState = intoMediator<EpisodesViewState.Episodes>()
    private val episodeState = intoMediator<EpisodesViewState.EpisodeUpdated>()

    fun getEpisodes(season: Int) {
        load {
            repository.getEpisodes()
                .onSuccess { onGetEpisodesSuccess(it, season) }
                .notifyError(errorLiveData)
        }
    }

    fun setEpisodeAsViewed(episode: Episode) {
        episode.isChecked = !episode.isChecked

        launch {
            repository.setEpisodeAsViewed(episode.id, episode.isChecked)
                .onSuccess { episodeState.postValue(EpisodesViewState.EpisodeUpdated(episode)) }
                .notifyError(errorLiveData)
        }
    }

    private fun onGetEpisodesSuccess(
        episodeList: List<Episode>,
        season: Int
    ) {
        val episodes = episodeList.filter { it.season.toInt() == season }

        episodesState.postValue(EpisodesViewState.Episodes(episodes))
    }

    sealed class EpisodesViewState : ViewState {
        data class Episodes(val episodes: List<Episode>) : EpisodesViewState()
        data class EpisodeUpdated(val episode: Episode) : EpisodesViewState()
    }
}