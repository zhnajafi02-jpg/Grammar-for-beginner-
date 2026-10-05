package com.example.data.model

object GrammarData {

  val minimalPairs: List<MinimalPair> = listOf(
    MinimalPair(
      id = "mp_1",
      wordA = "Can",
      wordB = "Can't",
      meaningA = "توانستن (تلفظ سریع و ضعیف /kən/)",
      meaningB = "نتوانستن (تلفظ پرتاکید /kænt/)",
      grammarNote = "تفاوت کلیدی در استرس و تلفظ کشیده Can't است."
    ),
    MinimalPair(
      id = "mp_2",
      wordA = "This",
      wordB = "These",
      meaningA = "این (مفرد، مصوت کوتاه /ðɪs/)",
      meaningB = "این‌ها (جمع، مصوت کشیده /ðiːz/)",
      grammarNote = "در These صدای آخر /z/ نرم و کشیده است."
    ),
    MinimalPair(
      id = "mp_3",
      wordA = "Walk",
      wordB = "Walks",
      meaningA = "راه رفتن (فاعل جمع)",
      meaningB = "راه می‌رود (سوم شخص مفرد he/she)",
      grammarNote = "پسوند s در سوم شخص صدای /s/ می‌دهد."
    ),
    MinimalPair(
      id = "mp_4",
      wordA = "Live",
      wordB = "Leave",
      meaningA = "زندگی کردن (مصوت کوتاه /lɪv/)",
      meaningB = "ترک کردن (مصوت کشیده /liːv/)",
      grammarNote = "توجه به تفاوت مصوت در جملات حال ساده."
    ),
    MinimalPair(
      id = "mp_5",
      wordA = "Play",
      wordB = "Played",
      meaningA = "بازی کردن (زمان حال)",
      meaningB = "بازی کرد (گذشته ساده با صدای /d/)",
      grammarNote = "پسوند ed بعد از مصوت‌ها صدای /d/ ملایم دارد."
    ),
    MinimalPair(
      id = "mp_6",
      wordA = "Watch",
      wordB = "Watches",
      meaningA = "تماشا کردن",
      meaningB = "تماشا می‌کند (سوم شخص با هجای اضافی /ɪz/)",
      grammarNote = "کلماتی که به ch ختم می‌شوند هجای /ɪz/ می‌گیرند."
    )
  )

  val units: List<GrammarUnit> = listOf(
    // UNIT 1
    GrammarUnit(
      id = "unit_1",
      number = 1,
      titleFa = "فعل‌های To Be و ضمایر فاعلی",
      titleEn = "To Be Verbs (Am, Is, Are) & Subject Pronouns",
      level = "مبتدی (A1)",
      summaryFa = "پایه‌ای‌ترین مبحث زبان انگلیسی برای معرفی خود، شغل، سن، صفات و موقعیت‌ها.",
      formula = "فاعل (Subject) + am / is / are + اسم / صفت / مکان",
      listeningKeyTipFa = "در گفتار محاوره‌ای انگلیسی، فعل‌های To Be تقریباً همیشه مخفف می‌شوند (I'm, She's, They're). در تمرین‌های شنیداری به صدای نرم /z/ در He's و صدای /m/ در I'm دقت کنید.",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. تقسیم‌بندی ضمایر و فعل To Be",
          bodyFa = "• ضمیر I همیشه با am می‌آید (مخفف: I'm)\n• ضمایر مفرد He / She / It همیشه با is می‌آیند (مخفف: He's, She's, It's)\n• ضمایر جمع You / We / They همیشه با are می‌آیند (مخفف: You're, We're, They're)"
        ),
        GrammarRuleSection(
          headingFa = "۲. منفی کردن و سوالی ساختن",
          bodyFa = "• برای منفی کردن فقط کافی است not اضافه کنید: I am not, She is not (isn't), We are not (aren't)\n• برای سوالی کردن، جای فاعل و فعل To Be را عوض کنید: Are you ready? / Is he a doctor?"
        )
      ),
      keyExamples = listOf(
        AudioSentence("u1_s1", "I am a student.", "من یک دانش‌آموز هستم.", "aɪ æm ə ˈstjuːdənt", "I'm تلفظ می‌شود /aɪm/"),
        AudioSentence("u1_s2", "She is a kind teacher.", "او معلم مهربانی است.", "ʃiː ɪz ə kaɪnd ˈtiːtʃər", "She's تلفظ می‌شود /ʃiːz/"),
        AudioSentence("u1_s3", "They are very happy today.", "آن‌ها امروز خیلی خوشحال هستند.", "ðeɪ ɑːr ˈveri ˈhæpi təˈdeɪ", "They're تلفظ می‌شود /ðeər/"),
        AudioSentence("u1_s4", "Are you ready for lunch?", "آیا برای ناهار آماده‌ای؟", "ɑːr juː ˈredi fɔːr lʌntʃ", "در سوال، Are با لحن صعودی تلفظ می‌شود"),
        AudioSentence("u1_s5", "It is not cold outside.", "بیرون هوا سرد نیست.", "ɪt ɪz nɑːt koʊld ˌaʊtˈsaɪd", "مخفف: It isn't cold")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u1_ex1",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "She is a kind teacher.",
          instructionFa = "به صوت گوش دهید و کلمات را به ترتیب صحیح بچینید:",
          scrambledWords = listOf("teacher", "is", "a", "kind", "She"),
          correctAnswer = "She is a kind teacher",
          persianTranslation = "او یک معلم مهربان است.",
          explanationFa = "ساختار جمله: فاعل (She) + فعل to be (is) + صفت و اسم (a kind teacher).",
          wrongExplanationFa = "در زبان انگلیسی ترتیب اجزای جمله خبری به صورت فاعل + فعل + متمم است. ابتدا باید She و سپس is و در نهایت a kind teacher قرار گیرد.",
          grammarRuleTipFa = "فرمول: فاعل + am/is/are + صفت/اسم"
        ),
        ListeningExercise(
          id = "u1_ex2",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "They are ready.",
          instructionFa = "کدام جمله در فایل صوتی ادا شد؟",
          options = listOf("They are ready.", "She is ready.", "He is ready."),
          correctAnswer = "They are ready.",
          persianTranslation = "آن‌ها آماده هستند.",
          explanationFa = "صوت با صدای ضمیر جمع They are شروع می‌شود.",
          wrongExplanationFa = "در صوت صدای نرم /ðeɪ ɑːr/ به معنای «آن‌ها هستند» شنیده شد، نه She is (/ʃiː ɪz/) یا He is (/hiː ɪz/).",
          grammarRuleTipFa = "تمایز شنیداری: They (جمع) در برابر He/She (مفرد)"
        ),
        ListeningExercise(
          id = "u1_ex3",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "My brother is at home now.",
          instructionFa = "جمله را بشنوید و کلمه خالی را انتخاب کنید:\nMy brother ___ at home now.",
          options = listOf("is", "are", "am"),
          correctAnswer = "is",
          persianTranslation = "برادرم الان خانه است.",
          explanationFa = "برای سوم شخص مفرد (My brother = He) از فعل is استفاده می‌کنیم.",
          wrongExplanationFa = "کلمه My brother معادل سوم شخص مفرد (He) است، بنابراین فقط فعل is صحیح است. فعل are برای جمع و am فقط برای ضمیر I است.",
          grammarRuleTipFa = "I -> am | He / She / It -> is | You / We / They -> are"
        ),
        ListeningExercise(
          id = "u1_ex4",
          type = ExerciseType.TRUE_FALSE,
          audioPrompt = "I am not tired. I am very energetic today.",
          instructionFa = "بر اساس صوت، آیا این جمله درست است؟\n«گوینده امروز خسته است.»",
          options = listOf("درست (True)", "نادرست (False)"),
          correctAnswer = "نادرست (False)",
          persianTranslation = "من خسته نیستم، امروز خیلی پرانرژی هستم.",
          explanationFa = "گوینده گفت: I am not tired یعنی خسته نیستم.",
          wrongExplanationFa = "گوینده با آوردن not بعد از am جمله را منفی کرد (I am not tired) و گفت که بسیار پرانرژی است، پس جمله صورت سوال نادرست است.",
          grammarRuleTipFa = "اضافه شدن not بعد از am/is/are جمله را منفی می‌کند."
        )
      )
    ),

    // UNIT 2
    GrammarUnit(
      id = "unit_2",
      number = 2,
      titleFa = "زمان حال ساده و کارهای روزمره",
      titleEn = "Present Simple Tense & Habits",
      level = "مبتدی (A1)",
      summaryFa = "بیان عادات روزانه، حقایق علمی و کارهایی که به طور روتین انجام می‌دهیم.",
      formula = "فاعل + فعل ساده (اضافه شدن s/es برای He/She/It)",
      listeningKeyTipFa = "یکی از مهم‌ترین نکات شنیداری: پسوند s آخر فعل سه تلفظ دارد:\n۱. صدای /s/ بعد از k, p, t (works, speaks)\n۲. صدای /z/ بعد از واکه‌ها و حروف نرم (plays, lives, reads)\n۳. صدای /ɪz/ با یک هجای اضافه بعد از ch, sh, ss, x (watches, washes)",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. قانون سوم شخص مفرد",
          bodyFa = "• برای I, You, We, They فعل بدون تغییر می‌آید: I work, They play\n• برای He, She, It به آخر فعل s یا es اضافه می‌شود: He works, She plays, It rains"
        ),
        GrammarRuleSection(
          headingFa = "۲. سوالی و منفی کردن با Do و Does",
          bodyFa = "• منفی: I don't work / He doesn't work (توجه: بعد از doesn't فعل ساده می‌شود!)\n• سوالی: Do you work? / Does he work?"
        )
      ),
      keyExamples = listOf(
        AudioSentence("u2_s1", "She speaks English every day.", "او هر روز انگلیسی صحبت می‌کند.", "ʃiː spiːks ˈɪŋɡlɪʃ ˈevri deɪ", "پسوند s در speaks با صدای /s/ شنیده می‌شود"),
        AudioSentence("u2_s2", "My father works in an office.", "پدرم در یک اداره کار می‌کند.", "maɪ ˈfɑːðər wɜːrks ɪn ən ˈɒfɪs", "works صدای /s/ دارد"),
        AudioSentence("u2_s3", "They watch television in the evening.", "آن‌ها عصرها تلویزیون تماشا می‌کنند.", "ðeɪ wɒtʃ ˈtelɪvɪʒn̩ ɪn ði ˈiːvnɪŋ", "چون فاعل They است s نمی‌گیرد"),
        AudioSentence("u2_s4", "Do you like classical music?", "آیا موسیقی کلاسیک دوست داری؟", "duː juː laɪk ˈklæsɪkl̩ ˈmjuːzɪk", "Do you در گفتار سریع به هم پیوسته می‌شود"),
        AudioSentence("u2_s5", "He doesn't drink tea.", "او چای نمی‌نوشد.", "hiː ˈdʌznt drɪŋk tiː", "بعد از doesn't فعل drink بدون s می‌آید")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u2_ex1",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "He lives in Canada.",
          instructionFa = "کدام فعل در صوت تلفظ شد؟ (به پسوند s دقت کنید)",
          options = listOf("He lives in Canada.", "He lived in Canada.", "They live in Canada."),
          correctAnswer = "He lives in Canada.",
          persianTranslation = "او در کانادا زندگی می‌کند.",
          explanationFa = "فعل lives با پسوند سوم شخص مفرد و صدای /z/ به وضوح شنیده می‌شود.",
          wrongExplanationFa = "در صوت پسوند s با صدای نرم /z/ در انتهای lives تلفظ شد. فعل live بدون s برای فاعل جمع است و lived مربوط به گذشته با صدای /d/ است.",
          grammarRuleTipFa = "قاعده: برای He/She/It در زمان حال ساده، فعل حتماً s یا es می‌گیرد."
        ),
        ListeningExercise(
          id = "u2_ex2",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "She speaks three languages fluently.",
          instructionFa = "کلمات را پس از شنیدن مرتب کنید:",
          scrambledWords = listOf("languages", "fluently", "She", "three", "speaks"),
          correctAnswer = "She speaks three languages fluently",
          persianTranslation = "او سه زبان را روان صحبت می‌کند.",
          explanationFa = "فاعل (She) + فعل (speaks) + مفعول (three languages) + قید (fluently).",
          wrongExplanationFa = "ترتیب گرامری: ابتدا فاعل (She)، سپس فعل (speaks)، بعد مفعول (three languages) و در پایان قید کیفیت (fluently) قرار می‌گیرد.",
          grammarRuleTipFa = "فرمول: فاعل + فعل + مفعول + قید حالت"
        ),
        ListeningExercise(
          id = "u2_ex3",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "Does your sister work here?",
          instructionFa = "به صوت گوش دهید و جای خالی را پر کنید:\n___ your sister work here?",
          options = listOf("Does", "Do", "Is"),
          correctAnswer = "Does",
          persianTranslation = "آیا خواهرت اینجا کار می‌کند؟",
          explanationFa = "خواهرت (your sister) سوم شخص مفرد است، بنابراین از Does استفاده می‌شود.",
          wrongExplanationFa = "فاعل your sister معادل she (سوم شخص مفرد) است و برای سوالی کردن حال ساده حتماً Does می‌خواهد. Do برای جمع و Is برای حال استمراری (با ing) است.",
          grammarRuleTipFa = "Does + فاعل مفرد (he/she/it) + فعل پایه؟"
        )
      )
    ),

    // UNIT 3
    GrammarUnit(
      id = "unit_3",
      number = 3,
      titleFa = "زمان حال استمراری (Present Continuous)",
      titleEn = "Present Continuous (Actions Happening Now)",
      level = "مبتدی (A1)",
      summaryFa = "بیان کارهایی که دقیقاً همین الان و در زمان صحبت کردن در حال رخ دادن هستند.",
      formula = "فاعل + am / is / are + فعل به همراه ing",
      listeningKeyTipFa = "در زبان انگلیسی طبیعی، حرف g در پایان ing به ندرت با تلفظ محکم /ɡ/ ادا می‌شود؛ بلکه یک صدای خیشومی نرم /ɪŋ/ یا حتی در گفتار عامیانه /ɪn/ دارد (مثل goin, doin). به ترکیب 'm doing' یا 're going' دقت کنید.",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. ساختار اصلی",
          bodyFa = "• I am studying English right now.\n• He/She/It is sleeping.\n• We/You/They are having lunch."
        ),
        GrammarRuleSection(
          headingFa = "۲. سوالی و منفی",
          bodyFa = "• منفی: I am not reading / She isn't watching\n• سوالی: What are you doing? / Is it raining?"
        )
      ),
      keyExamples = listOf(
        AudioSentence("u3_s1", "I am listening to an audio lesson.", "دارم به یک درس صوتی گوش می‌دهم.", "aɪ æm ˈlɪsnɪŋ tuː ən ˈɔːdiəʊ ˈlesn̩", "listening تلفظ می‌شود /ˈlɪs.nɪŋ/"),
        AudioSentence("u3_s2", "Look! It is raining outside.", "نگاه کن! بیرون دارد باران می‌بارد.", "lʊk ɪt ɪz ˈreɪnɪŋ ˌaʊtˈsaɪd", "It is raining نشان‌دهنده همین الان است"),
        AudioSentence("u3_s3", "What are you doing now?", "الان داری چه‌کار می‌کنی؟", "wɒt ɑːr juː ˈduːɪŋ naʊ", "لحن سوالی متداول در مکالمه"),
        AudioSentence("u3_s4", "They are playing football in the garden.", "آن‌ها در باغچه دارند فوتبال بازی می‌کنند.", "ðeɪ ɑːr ˈpleɪɪŋ ˈfʊtbɔːl ɪn ðə ˈɡɑːdn̩", "they are playing")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u3_ex1",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "I am listening to music now.",
          instructionFa = "جمله شنیده شده را با چیدن کلمات بازسازی کنید:",
          scrambledWords = listOf("music", "am", "to", "listening", "I", "now"),
          correctAnswer = "I am listening to music now",
          persianTranslation = "من الان دارم به موسیقی گوش می‌دهم.",
          explanationFa = "ساختار استمراری: I am + listening to + music + now.",
          wrongExplanationFa = "در زبان انگلیسی بعد از فعل listen حتماً حرف اضافه to می‌آید (listening to music). همچنین قید زمان now در انتهای جمله قرار می‌گیرد.",
          grammarRuleTipFa = "فرمول استمراری: فاعل + am/is/are + فعل دارای ing + مفعول"
        ),
        ListeningExercise(
          id = "u3_ex2",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "He is reading a book.",
          instructionFa = "آیا عمل در گذشته انجام شده یا همین الان در جریان است؟",
          options = listOf("همین الان در جریان است (He is reading)", "در گذشته بوده است (He read)"),
          correctAnswer = "همین الان در جریان است (He is reading)",
          persianTranslation = "او دارد کتاب می‌خواند.",
          explanationFa = "شنیدن is reading نشان‌دهنده زمان حال استمراری است.",
          wrongExplanationFa = "شنیدن فعل کمکی is به همراه پسوند ing در reading مشخصه قطعی زمان حال استمراری است. در گذشته فقط He read گفته می‌شد.",
          grammarRuleTipFa = "فعل to be + ing = کاری که دقیقاً در همین لحظه در جریان است"
        ),
        ListeningExercise(
          id = "u3_ex3",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "The children are playing outside.",
          instructionFa = "کلمه خالی را بر اساس صوت انتخاب کنید:\nThe children ___ playing outside.",
          options = listOf("are", "is", "am"),
          correctAnswer = "are",
          persianTranslation = "بچه‌ها دارند بیرون بازی می‌کنند.",
          explanationFa = "کلمه children اسم جمع است، بنابراین are می‌گیرد.",
          wrongExplanationFa = "کلمه children جمع بی‌قاعده child (کودک) به معنی کودکان است و فاعل جمع همیشه فعل are می‌گیرد نه is.",
          grammarRuleTipFa = "اسامی جمع بی‌قاعده (children, people, feet) همگی فعل جمع می‌گیرند."
        )
      )
    ),

    // UNIT 4
    GrammarUnit(
      id = "unit_4",
      number = 4,
      titleFa = "حروف تعریف A, An, The و اسم‌ها",
      titleEn = "Articles (A, An, The) & Singular/Plural Nouns",
      level = "مبتدی (A1)",
      summaryFa = "آشنایی با نحوه مشخص یا نامشخص کردن اسامی در انگلیسی و تغییرات تلفظی آن‌ها.",
      formula = "a + صدای صامت / an + صدای مصوت (a, e, i, o, u) / the + اسم مشخص",
      listeningKeyTipFa = "مهم‌ترین نکته شنیداری:\n۱. حرف a قبل از صداهای صدادار تبدیل به an می‌شود تا گفتار روان باشد: an apple, an hour (چون h خوانده نمی‌شود).\n۲. کلمه the قبل از حروف صدادار به شکل /ðiː/ (با یای کشیده) تلفظ می‌شود: The apple /ðiː æpl̩/ اما The book /ðə bʊk/.",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. تفاوت A و An بر اساس صدا (نه املا!)",
          bodyFa = "• ملاک فقط صدای اول کلمه است:\n- a university (چون با صدای «ی» شروع می‌شود!)\n- an hour (چون صدای اول «آ» است و h تلفظ نمی‌شود)"
        ),
        GrammarRuleSection(
          headingFa = "۲. حرف تعریف مشخص The",
          bodyFa = "وقتی چیزی را می‌شناسیم یا قبلاً ذکر شده است یا در دنیا یکتاست: The sun, The moon, The teacher we met."
        )
      ),
      keyExamples = listOf(
        AudioSentence("u4_s1", "I eat an apple every morning.", "من هر روز صبح یک سیب می‌خورم.", "aɪ iːt ən ˈæpl̩ ˈevri ˈmɔːrnɪŋ", "an با apple پیوند می‌خورد: an-apple"),
        AudioSentence("u4_s2", "She bought a new car yesterday.", "او دیروز یک ماشین نو خرید.", "ʃiː bɔːt ə njuː kɑːr ˈjestərdeɪ", "a new car"),
        AudioSentence("u4_s3", "The sun is very bright today.", "خورشید امروز خیلی درخشان است.", "ðə sʌn ɪz ˈveri braɪt təˈdeɪ", "The sun چون خورشید یکتاست"),
        AudioSentence("u4_s4", "Can you wait for an hour?", "می‌توانی یک ساعت صبر کنی؟", "kæn juː weɪt fɔːr ən ˈaʊər", "an hour چون h در hour تلفظ نمی‌شود")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u4_ex1",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "I saw an elephant at the zoo.",
          instructionFa = "کدام حرف تعریف در صوت ادا شد؟\nI saw ___ elephant at the zoo.",
          options = listOf("an", "a", "the"),
          correctAnswer = "an",
          persianTranslation = "من یک فیل در باغ‌وحش دیدم.",
          explanationFa = "قبل از کلمه elephant که با مصوت /e/ شروع می‌شود، an می‌آید.",
          wrongExplanationFa = "کلمه elephant با صدای صدادار /e/ شروع می‌شود. اگر a بیاید گفتار ناهنجار می‌شود (a elephant اشتباه است)؛ بنابراین حتماً an elephant صحیح است.",
          grammarRuleTipFa = "قاعده: قبل از کلماتی که با صدای مصوت (vowel sound) شروع می‌شوند از an استفاده می‌کنیم."
        ),
        ListeningExercise(
          id = "u4_ex2",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "The sun rises in the east.",
          instructionFa = "جمله شنیده شده را مرتب کنید:",
          scrambledWords = listOf("east", "in", "rises", "The", "sun", "the"),
          correctAnswer = "The sun rises in the east",
          persianTranslation = "خورشید از شرق طلوع می‌کند.",
          explanationFa = "The sun (فاعل مشخص) + rises (فعل سوم شخص) + in the east (مکان مشخص).",
          wrongExplanationFa = "هم خورشید و هم جهت‌های جغرافیایی پدیده‌هایی یکتا و مشخص هستند و باید حرف تعریف The بگیرند: The sun و in the east.",
          grammarRuleTipFa = "The برای اسامی منحصر به فرد در طبیعت (The sun, The moon) به کار می‌رود."
        ),
        ListeningExercise(
          id = "u4_ex3",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "She waited for an hour.",
          instructionFa = "کدام عبارت در صوت ادا شد؟",
          options = listOf("for an hour", "for a hour", "for our hour"),
          correctAnswer = "for an hour",
          persianTranslation = "او یک ساعت منتظر ماند.",
          explanationFa = "کلمه hour با an پیوسته تلفظ می‌شود: /fɔːr ən ˈaʊər/.",
          wrongExplanationFa = "در کلمه hour حرف h اصلاً خوانده نمی‌شود و تلفظ آن مثل our است (/ˈaʊər/). چون با صدای مصوت شروع می‌شود، باید an hour گفته شود نه a hour.",
          grammarRuleTipFa = "ملاک انتخاب a یا an صدای آغازین کلمه است نه املای آن!"
        )
      )
    ),

    // UNIT 5
    GrammarUnit(
      id = "unit_5",
      number = 5,
      titleFa = "افعال مدال: Can و Can't (توانستن)",
      titleEn = "Modal Verbs: Can and Can't (Ability & Requests)",
      level = "مبتدی (A1)",
      summaryFa = "بیان توانایی‌ها، مهارت‌ها و درخواست‌های محترمانه به همراه آموزش تمایز شنیداری مثبت و منفی.",
      formula = "فاعل + can / can't + فعل پایه (بدون to یا s)",
      listeningKeyTipFa = "مهم‌ترین راز شنیداری زبان‌آموزان!\nدر حالت مثبت، can بی‌تاکید است و خیلی سریع و ضعیف به صورت /kən/ گفته می‌شود (مثلاً I can go = /aɪ kən ɡoʊ/).\nاما can't منفی است، بنابراین استرس و تاکید زیادی دارد و با صدای کشیده /kænt/ یا /kɑːnt/ ادا می‌شود. پس اگر صدا قوی و کشیده بود منفی است!",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. بیان توانایی",
          bodyFa = "• برای همه ضمایر یکسان است:\nI can speak, She can speak, They can speak\n(هیچ وقت s سوم شخص نمی‌گیرد!)"
        ),
        GrammarRuleSection(
          headingFa = "۲. شکل منفی",
          bodyFa = "• Can not یا Can't (مخفف)\nمثال: I can't drive yet (هنوز نمی‌توانم رانندگی کنم)"
        )
      ),
      keyExamples = listOf(
        AudioSentence("u5_s1", "I can swim very well.", "من می‌توانم خیلی خوب شنا کنم.", "aɪ kən swɪm ˈveri wel", "can خیلی سریع و ضعیف: /kən/"),
        AudioSentence("u5_s2", "I can't hear you clearly.", "صدایتان را واضح نمی‌شنوم.", "aɪ kænt hɪər juː ˈklɪəli", "can't با استرس محکم و کشیده"),
        AudioSentence("u5_s3", "Can you speak more slowly, please?", "می‌توانید آرام‌تر صحبت کنید، لطفاً؟", "kən juː spiːk mɔːr ˈsləʊli pliːz", "درخواست محترمانه"),
        AudioSentence("u5_s4", "She can play the piano beautifully.", "او می‌تواند پیانو را زیبا بنوازد.", "ʃiː kən pleɪ ðə piˈænəʊ ˈbjuːtɪfli", "can play بدون s سوم شخص")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u5_ex1",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "I can't come to the party tonight.",
          instructionFa = "آیا گوینده به مهمانی می‌آید یا نمی‌تواند بیاید؟",
          options = listOf("نمی‌تواند بیاید (I can't come)", "می‌تواند بیاید (I can come)"),
          correctAnswer = "نمی‌تواند بیاید (I can't come)",
          persianTranslation = "امشب نمی‌توانم به مهمانی بیایم.",
          explanationFa = "استرس شدید روی کلمه can't نشانه قطعی منفی بودن جمله است.",
          wrongExplanationFa = "کلمه can't با استرس محکم و کشیده /kænt/ بیان شد. اگر مثبت بود (can)، خیلی سریع و کوتاه به صورت /kən/ گفته می‌شد و تأکید روی فعل come بود.",
          grammarRuleTipFa = "شنیدن can't منفی همیشه همراه با استرس ضربه‌دار و مصوت کشیده است."
        ),
        ListeningExercise(
          id = "u5_ex2",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "Can you help me with this?",
          instructionFa = "به درخواست صوتی گوش دهید و جمله را بسازید:",
          scrambledWords = listOf("help", "this?", "Can", "me", "you", "with"),
          correctAnswer = "Can you help me with this?",
          persianTranslation = "می‌توانی در این مورد کمکم کنی؟",
          explanationFa = "در سوال با can: Can + you + help + me + with this?",
          wrongExplanationFa = "در جملات درخواستی و سوالی با فعل کمکی can، همیشه کلمه Can در ابتدای جمله و سپس فاعل you و فعل پایه help قرار می‌گیرد.",
          grammarRuleTipFa = "فرمول درخواست محترمانه: ?Can + فاعل + فعل پایه"
        ),
        ListeningExercise(
          id = "u5_ex3",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "He can speak Spanish very well.",
          instructionFa = "کدام کلمه شنیده شد؟\nHe ___ speak Spanish very well.",
          options = listOf("can", "can't", "couldn't"),
          correctAnswer = "can",
          persianTranslation = "او می‌تواند به خوبی اسپانیایی صحبت کند.",
          explanationFa = "تلفظ ضعیف و سریع /kən/ نشانه can مثبت است.",
          wrongExplanationFa = "در صوت صدای ضعیف /kən/ ادا شد که فرم مثبت can است. فرم منفی can't کشیده و با استرس شدید تلفظ می‌شود.",
          grammarRuleTipFa = "در جملات مثبت can فرم ضعیف (Weak form) /kən/ دارد."
        )
      )
    ),

    // UNIT 6
    GrammarUnit(
      id = "unit_6",
      number = 6,
      titleFa = "زمان گذشته ساده (Past Simple & Was/Were)",
      titleEn = "Past Simple: Was/Were & Regular/Irregular Verbs",
      level = "مبتدی (A1)",
      summaryFa = "صحبت کردن درباره وقایع تمام شده در گذشته به همراه بررسی نحوه شنیدن پسوند ed.",
      formula = "فاعل + was / were یا فعل زمان گذشته (با قاعده: ed / بی‌قاعده: went, had)",
      listeningKeyTipFa = "پسوند ed افعال باقاعده در گذشته ۳ حالت شنیداری دارد:\n۱. صدای /t/ بعد از p, k, f, s, sh, ch (مانند: walked, helped, washed)\n۲. صدای /d/ بعد از مصوت‌ها و حروف نرم (مانند: played, lived, cleaned)\n۳. صدای /ɪd/ با یک هجای اضافه فقط بعد از t و d (مانند: wanted, needed, decided)",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. گذشته فعل بودن (Was و Were)",
          bodyFa = "• برای I, He, She, It از Was استفاده می‌شود\n• برای You, We, They از Were استفاده می‌شود\nمثال: I was at home / They were happy"
        ),
        GrammarRuleSection(
          headingFa = "۲. سوالی و منفی در گذشته ساده",
          bodyFa = "• منفی با didn't + فعل ساده: I didn't go (نه I didn't went!)\n• سوالی با Did + فاعل + فعل ساده: Did you see him?"
        )
      ),
      keyExamples = listOf(
        AudioSentence("u6_s1", "I was very tired yesterday evening.", "من دیروز عصر خیلی خسته بودم.", "aɪ wəz ˈveri ˈtaɪərd ˈjestərdeɪ ˈiːvnɪŋ", "was به صورت ضعیف /wəz/"),
        AudioSentence("u6_s2", "We played tennis in the afternoon.", "ما بعدازظهر تنیس بازی کردیم.", "wiː pleɪd ˈtenɪs ɪn ði ˌɑːftəˈnuːn", "played با صدای /d/ در پایان"),
        AudioSentence("u6_s3", "She visited her grandparents last week.", "او هفته گذشته به دیدن پدربزرگ و مادربزرگش رفت.", "ʃiː ˈvɪzɪtɪd hɜːr ˈɡrænpeərənts lɑːst wiːk", "visited با صدای /ɪd/ هجای اضافی دارد"),
        AudioSentence("u6_s4", "Did you enjoy the movie?", "آیا از فیلم لذت بردی؟", "dɪd juː ɪnˈdʒɔɪ ðə ˈmuːvi", "Did you در گفتار سریع به شکل /dɪdʒuː/ شنیده می‌شود")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u6_ex1",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "They cleaned the house yesterday.",
          instructionFa = "آیا عمل در گذشته انجام گرفته یا روتین همیشگی است؟",
          options = listOf("در گذشته انجام شده (cleaned)", "روتین و حال ساده (clean)"),
          correctAnswer = "در گذشته انجام شده (cleaned)",
          persianTranslation = "آن‌ها دیروز خانه را تمیز کردند.",
          explanationFa = "شنیدن پسوند /d/ در انتهای کلمه cleaned نشان‌دهنده زمان گذشته است.",
          wrongExplanationFa = "پسوند ed در انتهای cleaned با صدای نرم /d/ تلفظ شد و قید زمان yesterday نشان می‌دهد کار در گذشته انجام شده است.",
          grammarRuleTipFa = "پسوند ed بعد از حروف نرم و واکه‌ها صدای /d/ می‌دهد."
        ),
        ListeningExercise(
          id = "u6_ex2",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "Where did you go last night?",
          instructionFa = "به سوال صوتی گوش داده و کلمات را مرتب کنید:",
          scrambledWords = listOf("go", "Where", "night?", "did", "you", "last"),
          correctAnswer = "Where did you go last night?",
          persianTranslation = "دیشب کجا رفتی؟",
          explanationFa = "ساختار سوال گذشته: Where + did + you + go + last night?",
          wrongExplanationFa = "در سوالات گذشته ساده، بعد از کلمه پرسشی Where فعل کمکی did و سپس فاعل you و در انتها فعل پایه go قرار می‌گیرد.",
          grammarRuleTipFa = "فرمول سوال گذشته: کلمه Wh + did + فاعل + فعل ساده؟"
        ),
        ListeningExercise(
          id = "u6_ex3",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "We were at the library yesterday.",
          instructionFa = "کدام کلمه شنیده شد؟\nWe ___ at the library yesterday.",
          options = listOf("were", "was", "are"),
          correctAnswer = "were",
          persianTranslation = "ما دیروز در کتابخانه بودیم.",
          explanationFa = "برای فاعل We در گذشته از were استفاده می‌شود.",
          wrongExplanationFa = "برای فاعل We در گذشته ساده همیشه were به کار می‌رود نه was. کلمه are برای زمان حال است اما در جمله قید yesterday (دیروز) آمده است.",
          grammarRuleTipFa = "گذشته فعل بودن: We/You/They -> were | I/He/She/It -> was"
        )
      )
    ),

    // UNIT 7
    GrammarUnit(
      id = "unit_7",
      number = 7,
      titleFa = "کلمات پرسشی Wh- و لحن گفتار (Intonation)",
      titleEn = "Wh- Question Words & Intonation",
      level = "مبتدی (A1)",
      summaryFa = "پرسیدن سوالات درباره کیستی، چیستی، مکان، زمان، دلیل و چگونگی با ریتم طبیعی.",
      formula = "کلمه Wh (Who, What, Where, When, Why, How) + فعل کمکی + فاعل + فعل اصلی؟",
      listeningKeyTipFa = "نکته فوق‌العاده مهم در لیسنینگ:\nسوالات بله/خیر (مثل Are you ready?) در انتهای جمله لحن صعودی (بالا رونده) دارند.\nاما سوالات Wh- در انتهای جمله لحن نزولی (Falling Intonation) دارند؛ صدا در کلمه آخر پایین می‌آید: Where do you LIVE? ↘",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. معانی کلمات پرسشی",
          bodyFa = "• Who: چه کسی؟\n• What: چه چیزی؟\n• Where: کجا؟\n• When: چه زمانی؟\n• Why: چرا؟\n• How: چطور / چگونه؟"
        ),
        GrammarRuleSection(
          headingFa = "۲. تلفظ متصل کلمات پرسشی با Do/Are",
          bodyFa = "• Where are you... اغلب سریع به صورت /werɑːr juː/ ادا می‌شود\n• What do you... اغلب به شکل /wʌt də juː/ یا /wʌddjə/ تلفظ می‌شود"
        )
      ),
      keyExamples = listOf(
        AudioSentence("u7_s1", "Where do you live?", "کجا زندگی می‌کنی؟", "weər duː juː lɪv", "لحن نزولی در کلمه live"),
        AudioSentence("u7_s2", "What is your favorite color?", "رنگ مورد علاقه شما چیست؟", "wɒt ɪz jɔːr ˈfeɪvərɪt ˈkʌlər", "What is به صورت What's تلفظ می‌شود"),
        AudioSentence("u7_s3", "When does the train arrive?", "قطار چه ساعتی می‌رسد؟", "wen dʌz ðə treɪn əˈraɪv", "When does the train arrive"),
        AudioSentence("u7_s4", "Why are you learning English?", "چرا در حال یادگیری زبان انگلیسی هستی؟", "waɪ ɑːr juː ˈlɜːnɪŋ ˈɪŋɡlɪʃ", "استفاده از Why برای پرسیدن دلیل")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u7_ex1",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "What do you do in your free time?",
          instructionFa = "کلمات را پس از شنیدن مرتب کنید:",
          scrambledWords = listOf("free", "do", "you", "What", "in", "time?", "your", "do"),
          correctAnswer = "What do you do in your free time?",
          persianTranslation = "در اوقات فراغت خود چه می‌کنی؟",
          explanationFa = "What + do + you + do + in your free time?",
          wrongExplanationFa = "در این سوال دو بار کلمه do می‌آید: اولی فعل کمکی سوالی (What do you...) و دومی فعل اصلی به معنی انجام دادن (...do in your free time).",
          grammarRuleTipFa = "What do you do = چه کاری انجام می‌دهی (شغل یا روتین)"
        ),
        ListeningExercise(
          id = "u7_ex2",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "Where does your teacher live?",
          instructionFa = "کلمه پرسشی شنیده شده را مشخص کنید:\n___ does your teacher live?",
          options = listOf("Where", "When", "Who"),
          correctAnswer = "Where",
          persianTranslation = "معلم شما کجا زندگی می‌کند؟",
          explanationFa = "کلمه Where برای سوال در مورد مکان پرسیده شد.",
          wrongExplanationFa = "چون فعل جمله live (زندگی کردن) است، سوال درباره مکان است و کلمه Where (کجا) درست است. When برای زمان و Who برای شخص است.",
          grammarRuleTipFa = "Where برای سوال از مکان، When برای زمان، Who برای شخص"
        ),
        ListeningExercise(
          id = "u7_ex3",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "Who called you this morning?",
          instructionFa = "سوال درباره چه کسی بود یا چه چیزی؟",
          options = listOf("درباره شخص بود (Who)", "درباره ساعت بود (When)", "درباره دلیل بود (Why)"),
          correctAnswer = "درباره شخص بود (Who)",
          persianTranslation = "چه کسی امروز صبح به تو زنگ زد؟",
          explanationFa = "شنیدن Who نشانه سوال در مورد شخص است.",
          wrongExplanationFa = "کلمه آغازین صوت Who (/huː/) بود که منحصراً برای اشخاص استفاده می‌شود: چه کسی تماس گرفت؟",
          grammarRuleTipFa = "کلمه پرسشی Who همیشه به فاعل یا مفعول انسانی اشاره دارد."
        )
      )
    ),

    // UNIT 8
    GrammarUnit(
      id = "unit_8",
      number = 8,
      titleFa = "ضمایر اشاره و مالکیت (This, That, These, Those)",
      titleEn = "Demonstratives & Possessives",
      level = "مبتدی (A1)",
      summaryFa = "اشاره به اشیا و افراد نزدیک و دور، و بیان مالکیت با ضمایر my, your, his, her.",
      formula = "This/That + اسم مفرد | These/Those + اسم جمع",
      listeningKeyTipFa = "بزرگترین چالش شنیداری بین This و These است:\n• This: مصوت کوتاه /ɪ/ و صدای سوت آرام /s/ در آخر: /ðɪs/\n• These: مصوت کشیده «ای» /iː/ و صدای زنبوری نرم /z/ در آخر: /ðiːz/\nبه کشش کلمه دقت کنید!",
      rulesFa = listOf(
        GrammarRuleSection(
          headingFa = "۱. جدول ضمایر اشاره",
          bodyFa = "• This: این (مفرد، نزدیک)\n• That: آن (مفرد، دور)\n• These: این‌ها (جمع، نزدیک)\n• Those: آن‌ها (جمع، دور)"
        ),
        GrammarRuleSection(
          headingFa = "۲. صفات ملکی (Possessive Adjectives)",
          bodyFa = "• My (مال من), Your (مال تو), His (مال او - مذکر), Her (مال او - مؤنث), Our (مال ما), Their (مال آن‌ها)"
        )
      ),
      keyExamples = listOf(
        AudioSentence("u8_s1", "This is my new smartphone.", "این گوشی هوشمند جدید من است.", "ðɪs ɪz maɪ njuː ˈsmɑːrtfoʊn", "This مصوت کوتاه /ðɪs/"),
        AudioSentence("u8_s2", "These shoes are very comfortable.", "این کفش‌ها خیلی راحت هستند.", "ðiːz ʃuːz ɑːr ˈveri ˈkʌmftəbl̩", "These مصوت کشیده /ðiːz/"),
        AudioSentence("u8_s3", "That car over there belongs to my brother.", "آن ماشین آنجا متعلق به برادرم است.", "ðæt kɑːr ˈoʊvər ðeər bɪˈlɔːŋz tuː maɪ ˈbrʌðər", "That برای اشاره به دور"),
        AudioSentence("u8_s4", "Are those your keys on the table?", "آیا آن‌ها کلیدهای شما روی میز هستند؟", "ɑːr ðoʊz jɔːr kiːz ɒn ðə ˈteɪbl̩", "Those برای جمع دور")
      ),
      exercises = listOf(
        ListeningExercise(
          id = "u8_ex1",
          type = ExerciseType.SOUND_CONTRAST,
          audioPrompt = "These books are very interesting.",
          instructionFa = "کدام کلمه در صوت ادا شد؟ (به مصوت کشیده یا کوتاه دقت کنید)",
          options = listOf("These (این کتاب‌ها - جمع)", "This (این کتاب - مفرد)"),
          correctAnswer = "These (این کتاب‌ها - جمع)",
          persianTranslation = "این کتاب‌ها بسیار جالب هستند.",
          explanationFa = "مصوت کشیده /ðiːz/ و فعل are نشان می‌دهند اسم جمع These است.",
          wrongExplanationFa = "کلمه These با مصوت کشیده «ای» (/ðiːz/) و همراه با فعل جمع are ادا شد. کلمه This کوتاه با صدای /s/ است و با is می‌آید.",
          grammarRuleTipFa = "These برای اسامی جمع نزدیک و This برای اسامی مفرد نزدیک"
        ),
        ListeningExercise(
          id = "u8_ex2",
          type = ExerciseType.WORD_ORDER,
          audioPrompt = "This is my favorite English book.",
          instructionFa = "جمله را پس از گوش دادن مرتب کنید:",
          scrambledWords = listOf("book", "favorite", "my", "This", "is", "English"),
          correctAnswer = "This is my favorite English book",
          persianTranslation = "این کتاب انگلیسی مورد علاقه من است.",
          explanationFa = "This is + my favorite + English book.",
          wrongExplanationFa = "ترتیب صفت‌ها در انگلیسی: صفت ملکی (my) قبل از صفت توصیفی (favorite) و بعد صفت ملیت (English) و در پایان اسم (book) می‌آید.",
          grammarRuleTipFa = "ترتیب کلمات: فاعل اشاره + is + صفت ملکی + صفت + اسم"
        ),
        ListeningExercise(
          id = "u8_ex3",
          type = ExerciseType.FILL_BLANK,
          audioPrompt = "Is that your brother waiting over there?",
          instructionFa = "کدام ضمیر اشاره شنیده شد؟\nIs ___ your brother waiting over there?",
          options = listOf("that", "those", "these"),
          correctAnswer = "that",
          persianTranslation = "آیا آن برادر شماست که آنجا منتظر است؟",
          explanationFa = "کلمه that برای مفرد در فاصله دور استفاده شد.",
          wrongExplanationFa = "کلمه brother مفرد است و با عبارت over there (در آنجا / دور) آمده است، بنابراین ضمیر مفرد دور یعنی that صحیح است. those و these برای جمع هستند.",
          grammarRuleTipFa = "That برای مفرد دور، Those برای جمع دور"
        )
      )
    )
  )

  fun getUnitById(id: String): GrammarUnit? {
    return units.find { it.id == id }
  }
}
