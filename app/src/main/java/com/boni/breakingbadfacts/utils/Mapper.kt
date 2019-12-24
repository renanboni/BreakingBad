package com.boni.breakingbadfacts.utils

import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.data.source.remote.model.EpisodeModel
import com.boni.breakingbadfacts.data.source.remote.model.QuoteModel
import com.boni.breakingbadfacts.features.model.Character
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
        series = season.orEmpty(),
        airDate = airDate.orEmpty(),
        characters = characters.orEmpty(),
        episode = episode.orEmpty(),
        season = season.orEmpty()
    )
}

fun List<EpisodeModel>.toEpisodes(): List<Episode> {
    return map { it.toEpisode() }
}