package com.github.zaneli.refinement.result

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.zipOrAccumulate
import com.github.zaneli.refinement.Skill

data class Player(
    val name: PlayerName,
    val level: PosInt,
    val skills: NonEmptyList<Skill>,
) {
  companion object {
    fun from(name: String, level: Int, skills: List<Skill>): Result<Player, List<String>> =
        zipOrAccumulate(
            { PlayerName.from(name) },
            { PosInt.from(level) },
            { NonEmptyList.from(skills) },
        ) { name, level, skills -> Player(name, level, skills) }
  }
}
