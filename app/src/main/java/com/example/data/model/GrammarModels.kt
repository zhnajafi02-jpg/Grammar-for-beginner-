package com.example.data.model

enum class ExerciseType {
  WORD_ORDER,       // گوش دادن و چیدن کلمات به ترتیب درست
  SOUND_CONTRAST,   // تمایز دو تلفظ نزدیک (مثلاً can vs can't یا s سوم شخص)
  FILL_BLANK,       // پر کردن جای خالی با شنیدن صوت کامل
  TRUE_FALSE,       // درست یا نادرست شنیداری
  DICTATION_CHOICE  // تشخیص دقیق جمله شنیده شده بین گزینه‌های انحرافی
}

data class AudioSentence(
  val id: String,
  val english: String,
  val persian: String,
  val phonetic: String = "",
  val audioTip: String = "",
  val focusWord: String = ""
)

data class DialogueLine(
  val speaker: String,
  val english: String,
  val persian: String
)

data class ListeningExercise(
  val id: String,
  val type: ExerciseType,
  val audioPrompt: String,
  val instructionFa: String,
  val options: List<String> = emptyList(),
  val scrambledWords: List<String> = emptyList(), // For WORD_ORDER
  val correctAnswer: String,
  val persianTranslation: String,
  val explanationFa: String,
  val wrongExplanationFa: String = "",
  val grammarRuleTipFa: String = "",
  val dialogueLines: List<DialogueLine> = emptyList()
)

data class GrammarUnit(
  val id: String,
  val number: Int,
  val titleFa: String,
  val titleEn: String,
  val level: String = "مبتدی (A1)",
  val summaryFa: String,
  val formula: String,
  val rulesFa: List<GrammarRuleSection>,
  val listeningKeyTipFa: String,
  val keyExamples: List<AudioSentence>,
  val exercises: List<ListeningExercise>
)

data class GrammarRuleSection(
  val headingFa: String,
  val bodyFa: String,
  val examples: List<AudioSentence> = emptyList()
)

data class MinimalPair(
  val id: String,
  val wordA: String,
  val wordB: String,
  val meaningA: String,
  val meaningB: String,
  val grammarNote: String
)
