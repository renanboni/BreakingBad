package com.boni.breakingbadfacts.utils

import com.boni.breakingbadfacts.data.source.local.model.EpisodeEntity
import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.model.DeathModel
import com.boni.breakingbadfacts.data.source.remote.model.EpisodeModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import com.boni.breakingbadfacts.features.model.Character
import com.boni.breakingbadfacts.features.model.Death
import com.boni.breakingbadfacts.features.model.Episode
import com.boni.breakingbadfacts.features.model.Quote

fun CharacterModel.toCharacter(): Character {
    return Character(
        id = id ?: 0,
        name = name.orEmpty(),
        birthday = birthday.orEmpty(),
        occupation = occupation.orEmpty(),
        img = img.orEmpty(),
        status = status.orEmpty(),
        appearance = appearance.orEmpty(),
        nickname = nickname.orEmpty(),
        portrayed = portrayed.orEmpty()
    )
}

fun List<CharacterModel>.toCharacters(): List<Character> {
    return map { it.toCharacter() }
}

fun QuoteModel.toQuote(): Quote {
    return Quote(
        id = id ?: 0,
        quote = quote.orEmpty(),
        author = author.orEmpty(),
        series = series.orEmpty()
    )
}

fun List<QuoteModel>.toQuotes(): List<Quote> {
    return map { it.toQuote() }
}

fun EpisodeModel.toEpisode(): Episode {
    return Episode(
        id = id ?: 0,
        title = title.orEmpty(),
        series = season.orEmpty().trim(),
        airDate = airDate.orEmpty(),
        characters = characters.orEmpty(),
        episode = episode.orEmpty(),
        season = season.orEmpty().trim()
    )
}

fun List<EpisodeModel>.toEpisodes(): List<Episode> {
    return map { it.toEpisode() }
}

fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        id = id,
        title = title,
        series = season.trim(),
        airDate = airDate,
        characters = characters,
        episode = episode,
        season = season.trim()
    )
}

fun EpisodeModel.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id ?: 0,
        title = title.orEmpty(),
        series = season.orEmpty().trim(),
        airDate = airDate.orEmpty(),
        characters = characters.orEmpty(),
        episode = episode.orEmpty(),
        season = season.orEmpty().trim()
    )
}

fun List<EpisodeModel>.toEpisodesEntity(): List<EpisodeEntity> {
    return map { it.toEpisodeEntity() }
}

@JvmName("episodeEntityToEpisode")
fun List<EpisodeEntity>.toEpisodes(): List<Episode> {
    return map { it.toEpisode() }
}

fun DeathModel.toDeath(): Death {
    return Death(
        id = id ?: 0,
        death = death.orEmpty(),
        cause = cause.orEmpty(),
        responsible = responsible.orEmpty(),
        lastWords = lastWords.orEmpty(),
        season = season ?: 0,
        episode = episode ?: 0,
        numberOfDeaths = numberOfDeaths ?: 0,
        img = ""
    )
}

fun List<DeathModel>.toDeaths(): List<Death> {
    return map { it.toDeath() }
}