package com.martinprice20.passwordmakermvi.views.word

import com.martinprice20.passwordmakermvi.model.PwWord

data class WordState(
    val wordOne: PwWord,
    val wordTwo: PwWord,
    val wordThree: PwWord,
    val randomWordsList: List<PwWord>,
    val savedWordsList: List<PwWord>
    )
