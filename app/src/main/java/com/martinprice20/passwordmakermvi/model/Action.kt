package com.martinprice20.passwordmakermvi.model

sealed class Action {
    class NumOneInc : Action()
    class NumOneDec : Action()
    class NumOneRand : Action()
    class NumTwoInc : Action()
    class NumTwoDec : Action()
    class NumTwoRand : Action()
    class NumThreeInc : Action()
    class NumThreeDec : Action()
    class NumThreeRand : Action()

}
