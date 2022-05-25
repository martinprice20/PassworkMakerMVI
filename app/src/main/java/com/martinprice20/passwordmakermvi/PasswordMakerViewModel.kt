package com.martinprice20.passwordmakermvi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martinprice20.passwordmakermvi.model.Action
import com.martinprice20.passwordmakermvi.model.NumberAction
import com.martinprice20.passwordmakermvi.model.PwNumber
import com.martinprice20.passwordmakermvi.model.PwWord
import com.martinprice20.passwordmakermvi.model.WordAction.*
import com.martinprice20.passwordmakermvi.repository.PasswordRepository
import com.martinprice20.passwordmakermvi.utils.RandomInputUtil
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import com.martinprice20.passwordmakermvi.views.number.NumberState
import com.martinprice20.passwordmakermvi.views.word.WordState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

const val MIN_NUM = 0
const val MAX_NUM = 99
const val DEC = "decrement"
const val INC = "increment"
enum class WordApiState {LOADING, ERROR, DONE}

class PasswordMakerViewModel @Inject constructor(
    val resources: ResourceProvider,
    val repository: PasswordRepository
    ): ViewModel() {

    val disposable: CompositeDisposable = CompositeDisposable()

    private val _numState : MutableLiveData<NumberState> = MutableLiveData()
    val numState : LiveData<NumberState>
        get() = _numState

    private val _wordState : MutableLiveData<WordState> = MutableLiveData()
    val wordState : LiveData<WordState>
        get() = _wordState

    private val _wordApiState : MutableLiveData<WordApiState> = MutableLiveData()
    val wordApiState: LiveData<WordApiState>
        get() = _wordApiState

    val words :  MutableList<PwWord> = mutableListOf(PwWord("Alpha"), PwWord("Bravo"), PwWord("Charlie"))
    val savedWords : MutableList<PwWord> = mutableListOf()

    init {
        _numState.value = NumberState(PwNumber(), PwNumber(), PwNumber())
        resetWordState()
        _wordApiState.value = WordApiState.DONE
        setWords()
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
            else -> {}
        }
    }

    private fun numberSetter(pwNum: PwNumber, mode: String) : PwNumber {
        return when(mode) {
            DEC -> PwNumber(if (pwNum.num <= MIN_NUM) MIN_NUM else pwNum.num.dec())
            else -> PwNumber(if (pwNum.num >= MAX_NUM) MAX_NUM else pwNum.num.inc())
        }
    }

    fun reduceWordState(action: Action) {
        when(action) {
            is WordOneRandom -> {
                _wordState.value = wordState.value!!.copy(wordOne = words[0])
            }
            is WordTwoRandom -> {
                _wordState.value = wordState.value!!.copy(wordTwo = words[1])
            }
            is WordThreeRandom -> {
              _wordState.value = wordState.value!!.copy(wordThree = words[2])
            }
            is ResetWords -> { resetWordState() }
            is SaveWordsAndContinue -> { saveWords(action.wordlist)}
            else -> {}
        }
    }

    private fun resetWordState() {
        _wordState.value = WordState(PwWord(), PwWord(), PwWord())
    }

    private fun saveWords(wordList: List<PwWord>) {
        savedWords.clear()
        savedWords.addAll(wordList)
    }

    private fun setWords() {
        disposable.add(

            repository.getWords(3, 5)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { _wordApiState.value = WordApiState.LOADING }
                .subscribe (
                    {
                        words.clear()
                        for (word in it) {
                            words.add(PwWord(word))
                        }
                        _wordApiState.postValue(WordApiState.DONE)
                    },
                    { _wordApiState.postValue(WordApiState.ERROR) }
                )
        )

    }
}