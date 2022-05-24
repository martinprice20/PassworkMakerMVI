package com.martinprice20.passwordmakermvi

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martinprice20.passwordmakermvi.model.Action
import com.martinprice20.passwordmakermvi.model.NumberAction
import com.martinprice20.passwordmakermvi.model.PwNumber
import com.martinprice20.passwordmakermvi.repository.PasswordRepository
import com.martinprice20.passwordmakermvi.utils.RandomInputUtil
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import com.martinprice20.passwordmakermvi.views.number.NumberState
import javax.inject.Inject

const val MIN_NUM = 0
const val MAX_NUM = 99
const val DEC = "decrement"
const val INC = "increment"

class PasswordMakerViewModel @Inject constructor(
    val resources: ResourceProvider,
    val repository: PasswordRepository): ViewModel() {

    val _numState :MutableLiveData<NumberState> = MutableLiveData()
    val numState : LiveData<NumberState>
        get() = _numState

    init {
        _numState.value = NumberState(PwNumber(), PwNumber(), PwNumber())

    }

    fun reduceNumberState(action: Action) {
        when (action) {
            NumberAction.NumOneRand
                -> _numState.value = numState.value!!.copy(num1 = PwNumber(RandomInputUtil.getRandomNumber()))
            NumberAction.NumOneInc
                -> _numState.value = numState.value!!
                    .copy(num1 = numberSetter(numState.value!!.num1, INC))
            NumberAction.NumOneDec
                -> _numState.value = numState.value!!
                    .copy(num1 = numberSetter(numState.value!!.num1, DEC))
            NumberAction.NumTwoRand
                -> _numState.value = numState.value!!.copy(num2 = PwNumber(RandomInputUtil.getRandomNumber()))
            NumberAction.NumTwoInc
                -> _numState.value = numState.value!!
                    .copy(num2 = numberSetter(numState.value!!.num2, INC))
            NumberAction.NumTwoDec
                -> _numState.value = _numState.value!!
                    .copy(num2 = numberSetter(numState.value!!.num2, DEC))
            NumberAction.NumThreeRand
                -> _numState.value =
                    numState.value!!.copy(num3 = PwNumber(RandomInputUtil.getRandomNumber()))
            NumberAction.NumThreeInc
                -> _numState.value = numState.value!!
                    .copy(num3 =numberSetter(numState.value!!.num3, INC))
            NumberAction.NumThreeDec
                -> _numState.value = numState.value!!
                    .copy(num3 = numberSetter(numState.value!!.num3, DEC))
            NumberAction.ResetNums
                -> _numState.value = NumberState(PwNumber(), PwNumber(), PwNumber())
        }
    }

    fun numberSetter(pwNum: PwNumber, mode: String) : PwNumber {
        return when(mode) {
            DEC -> PwNumber(if (pwNum.num <= MIN_NUM) MIN_NUM else pwNum.num.dec())
            else -> PwNumber(if (pwNum.num >= MAX_NUM) MAX_NUM else pwNum.num.inc())
        }


    }
}