package com.martinprice20.passwordmakermvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martinprice20.passwordmakermvi.model.Action
import com.martinprice20.passwordmakermvi.model.PwNumber
import com.martinprice20.passwordmakermvi.utils.RandomInputUtil
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import com.martinprice20.passwordmakermvi.views.number.NumberState

class PasswordMakerViewModel(): ViewModel() {
    constructor(utils: ResourceProvider) : this()

    val _numState :MutableLiveData<NumberState> = MutableLiveData()
    val numState : LiveData<NumberState>
        get() = _numState

    init {
        _numState.value = NumberState(PwNumber(), PwNumber(), PwNumber())
    }

    fun reduceNumberState(action: Action) {
        when (action) {
            action -> println("some action taken")
            Action.NumOneRand()
                -> _numState.value =
                    numState.value!!.copy(num1 = PwNumber(RandomInputUtil.getRandomNumber()))
            Action.NumOneInc()
                -> _numState.value = numState.value!!
                    .copy(
                        num1 = PwNumber(numState.value!!.num1.num.inc())
                    )
            Action.NumOneDec()
                -> _numState.value = numState.value!!
                    .copy(
                        num1 = PwNumber(numState.value!!.num1.num.dec())
                    )
            Action.NumTwoRand()
                -> _numState.value =
                    numState.value!!.copy(num2 = PwNumber(RandomInputUtil.getRandomNumber()))
            Action.NumTwoInc()
                -> _numState.value = numState.value!!
                    .copy(
                        num2 = PwNumber(numState.value!!.num2.num.inc())
                    )
            Action.NumTwoDec()
                -> _numState.value = numState.value!!
                    .copy(
                        num2 = PwNumber(numState.value!!.num2.num.dec())
                    )
            Action.NumThreeRand()
                -> _numState.value =
                    numState.value!!.copy(num3 = PwNumber(RandomInputUtil.getRandomNumber()))
            Action.NumThreeInc()
                -> _numState.value = numState.value!!
                    .copy(
                        num3 = PwNumber(numState.value!!.num3.num.inc())
                    )
            Action.NumThreeDec()
                -> _numState.value = numState.value!!
                    .copy(
                        num3 = PwNumber(numState.value!!.num3.num.dec())
                    )
            else -> throw IllegalArgumentException("this action could not be found")
        }
    }
}