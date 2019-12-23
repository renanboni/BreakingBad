package com.boni.breakingbadfacts.utils

import com.boni.breakingbadfacts.data.source.remote.model.CharacterModel
import com.boni.breakingbadfacts.features.model.Character


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