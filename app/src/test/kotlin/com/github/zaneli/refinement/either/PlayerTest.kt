package com.github.zaneli.refinement.either

import com.github.zaneli.refinement.Skill
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PlayerTest :
    FunSpec({
      context("Player.from") {
        test("success") {
          val table =
              table(
                  headers("name", "level", "skills"),
                  row("abc", 1, listOf(Skill.ATTACK)),
                  row("あいうえお", 1, listOf(Skill.ATTACK)),
              )
          forAll(table) { name, level, skills ->
            val got = Player.from(name, level, skills).getOrNull().shouldNotBeNull()
            got.name.value shouldBe name
            got.level.value shouldBe level
            got.skills.toList() shouldContainExactlyInAnyOrder skills
          }
        }
        test("failure") {
          val table =
              table(
                  headers("name", "level", "skills", "want"),
                  row("", 1, listOf(Skill.ATTACK), listOf("空文字です")),
                  row("abcdefghijk", 1, listOf(Skill.ATTACK), listOf("10文字を超えています")),
                  row("ab c", 1, listOf(Skill.ATTACK), listOf("空白文字が含まれています")),
                  row("abc", 0, listOf(Skill.ATTACK), listOf("1未満です")),
                  row("abc", 1, listOf(), listOf("空リストです")),
                  row("", -1, listOf(), listOf("空文字です", "1未満です", "空リストです")),
              )
          forAll(table) { name, level, skills, want ->
            val got = Player.from(name, level, skills).leftOrNull().shouldNotBeNull()
            got.toList() shouldContainExactlyInAnyOrder want
          }
        }
      }
    })
