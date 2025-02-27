package com.github.zaneli.refinement.result

import com.github.zaneli.refinement.Skill
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class NonEmptyListTest :
    FunSpec({
      context("NonEmptyList.from") {
        test("success") {
          val table =
              table(
                  headers("list"),
                  row(listOf(Skill.ATTACK)),
                  row(listOf(Skill.ATTACK, Skill.DEFENSE)),
              )
          forAll(table) { list ->
            val got = NonEmptyList.from(list).value
            got.head shouldBe list[0]
            got.tail shouldBe list.drop(1)
            got.toList() shouldBe list
          }
        }
        test("failure") {
          val table =
              table(
                  headers("list", "want"),
                  row(listOf<Skill>(), "空リストです"),
              )
          forAll(table) { list, want ->
            val got = NonEmptyList.from(list).error
            got shouldBe want
          }
        }
      }
    })
