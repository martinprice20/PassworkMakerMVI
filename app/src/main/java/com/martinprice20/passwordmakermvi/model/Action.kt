package com.martinprice20.passwordmakermvi.model

sealed class Action

sealed class NumberAction : Action() {
    object NumOneInc : NumberAction()
    object NumOneDec : NumberAction()
    object NumOneRand : NumberAction()
    object NumTwoInc : NumberAction()
    object NumTwoDec : NumberAction()
    object NumTwoRand : NumberAction()
    object NumThreeInc : NumberAction()
    object NumThreeDec : NumberAction()
    object NumThreeRand : NumberAction()
    object ResetNums: NumberAction()

}

sealed class WordAction : Action() {
    object WordOneRandom: WordAction()
    object WordTwoRandom: WordAction()
    object WordThreeRandom: WordAction()
    data class SaveWordsAndContinue(val wordlist: List<PwWord>): WordAction()
    object ResetWords: WordAction()
}
