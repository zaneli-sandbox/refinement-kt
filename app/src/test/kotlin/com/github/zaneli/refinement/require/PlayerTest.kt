package com.github.zaneli.refinement.require

import com.github.zaneli.refinement.Skill
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class PlayerTest :
    FunSpec({
      context("Player") {
        test("success") {
          val table =
              table(
                  headers("name", "level", "skills"),
                  row("abc", 1, listOf(Skill.ATTACK)),
                  row("あいうえお", 1, listOf(Skill.ATTACK)),
              )
          forAll(table) { name, level, skills ->
            val got = Player(name, level, skills)
            got.name shouldBe name
            got.level shouldBe level
            got.skills shouldContainExactlyInAnyOrder skills
          }
        }
        test("failure") {
          val table =
              table(
                  headers("name", "level", "skills", "want"),
                  row("", 1, listOf(Skill.ATTACK), "名前が空文字です"),
                  row("abcdefghijk", 1, listOf(Skill.ATTACK), "名前が10文字を超えています"),
                  row("ab c", 1, listOf(Skill.ATTACK), "名前に空白文字が含まれています"),
                  row("abc", 0, listOf(Skill.ATTACK), "レベルが1未満です"),
                  row("abc", 1, listOf(), "スキルが空リストです"),
                  row("", -1, listOf(), "名前が空文字です"),
              )
          forAll(table) { name, level, skills, want ->
            val got = shouldThrow<IllegalArgumentException> { Player(name, level, skills) }
            got.message shouldBe want
          }
        }
      }
    })
