package com.github.zaneli.refinement.require

import com.github.zaneli.refinement.Skill

data class Player(
    val name: String,
    val level: Int,
    val skills: List<Skill>,
) {
  init {
    require(name.isNotEmpty()) { "名前が空文字です" }
    require(name.all { !it.isWhitespace() }) { "名前に空白文字が含まれています" }
    require(name.length <= 10) { "名前が10文字を超えています" }
    require(level >= 1) { "レベルが1未満です" }
    require(skills.isNotEmpty()) { "スキルが空リストです" }
  }
}
