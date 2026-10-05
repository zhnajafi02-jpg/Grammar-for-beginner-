package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun grammarUnits_arePopulated() {
    val units = com.example.data.model.GrammarData.units
    assertTrue(units.isNotEmpty())
    assertTrue(units.size >= 8)
    units.forEach { unit ->
      assertTrue(unit.titleFa.isNotEmpty())
      assertTrue(unit.keyExamples.isNotEmpty())
      assertTrue(unit.exercises.isNotEmpty())
    }
  }

  @Test
  fun minimalPairs_areConfigured() {
    val pairs = com.example.data.model.GrammarData.minimalPairs
    assertTrue(pairs.isNotEmpty())
  }

  @Test
  fun exercises_haveExplanationsAndFeedbackData() {
    val units = com.example.data.model.GrammarData.units
    units.forEach { unit ->
      unit.exercises.forEach { ex ->
        assertTrue("Exercise ${ex.id} must have explanationFa", ex.explanationFa.isNotBlank())
        assertTrue("Exercise ${ex.id} must have persianTranslation", ex.persianTranslation.isNotBlank())
        assertTrue("Exercise ${ex.id} must have wrongExplanationFa", ex.wrongExplanationFa.isNotBlank())
      }
    }
  }
}
