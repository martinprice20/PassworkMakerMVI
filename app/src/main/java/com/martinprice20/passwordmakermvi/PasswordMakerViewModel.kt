package com.martinprice20.passwordmakermvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martinprice20.passwordmakermvi.model.*
import com.martinprice20.passwordmakermvi.model.WordAction.*
import com.martinprice20.passwordmakermvi.repository.PasswordRepository
import com.martinprice20.passwordmakermvi.utils.ResourceAndUtilsProvider
import com.martinprice20.passwordmakermvi.views.number.NumberState
import com.martinprice20.passwordmakermvi.views.symbol.SymbolState
import com.martinprice20.passwordmakermvi.views.word.WordState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

const val MIN_NUM = 0
const val MAX_NUM = 99
const val DEC = "decrement"
const val INC = "increment"
enum class WordApiState {LOADING, ERROR, DONE}

class PasswordMakerViewModel @Inject constructor(
    val resources: ResourceAndUtilsProvider,
    private val repository: PasswordRepository
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

    private val _symbolState : MutableLiveData<SymbolState> = MutableLiveData()
    val symbolState : LiveData<SymbolState>
        get() = _symbolState

    private var allSymbols: List<String>
    lateinit var fiveSymbols : MutableLiveData<MutableList<String>>


    init {
        _numState.value = NumberState(PwNumber(), PwNumber(), PwNumber())
        _wordApiState.value = WordApiState.DONE
        allSymbols = resources.getSymbolsList()
        setFiveSymbols()
    }

    fun initWordState() {
        _wordState.value =  WordState(PwWord(), PwWord(), PwWord(), listOf(), listOf())
        setRandomWords()
    }

    /* Numbers Fragment Area */

    fun reduceNumberState(action: Action) {
        when (action) {
            NumberAction.NumOneRand
                -> _numState.value = numState.value!!.copy(num1 = PwNumber(resources.getRandomNum0To99()))
            NumberAction.NumOneInc
                -> _numState.value = numState.value!!
                    .copy(num1 = numberSetter(numState.value!!.num1, INC))
            NumberAction.NumOneDec
                -> _numState.value = numState.value!!
                    .copy(num1 = numberSetter(numState.value!!.num1, DEC))
            NumberAction.NumTwoRand
                -> _numState.value = numState.value!!.copy(num2 = PwNumber(resources.getRandomNum0To99()))
            NumberAction.NumTwoInc
                -> _numState.value = numState.value!!
                    .copy(num2 = numberSetter(numState.value!!.num2, INC))
            NumberAction.NumTwoDec
                -> _numState.value = _numState.value!!
                    .copy(num2 = numberSetter(numState.value!!.num2, DEC))
            NumberAction.NumThreeRand
                -> _numState.value =
                    numState.value!!.copy(num3 = PwNumber(resources.getRandomNum0To99()))
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

    /* Words Fragment Area */

    fun reduceWordState(action: Action) {
        when(action) {
            is WordOneRandom -> {
                _wordState.value = wordState.value!!.copy(wordOne = wordState.value!!.randomWordsList[Random.nextInt(
                    wordState.value!!.randomWordsList.size)])
            }
            is WordTwoRandom -> {
                _wordState.value = wordState.value!!.copy(wordTwo = wordState.value!!.randomWordsList[Random.nextInt(
                    wordState.value!!.randomWordsList.size)])
            }
            is WordThreeRandom -> {
              _wordState.value = wordState.value!!.copy(wordThree = wordState.value!!.randomWordsList[Random.nextInt(
                  wordState.value!!.randomWordsList.size)])
            }
            is ResetWords -> { resetWordState() }
            is SaveWordsAndContinue -> { saveWords(action.wordlist)}
            else -> {}
        }
    }

    private fun resetWordState() {
        _wordState.value = wordState.value!!.copy(wordOne = PwWord(), wordTwo = PwWord(), wordThree = PwWord())
    }

    private fun saveWords(wordList: List<PwWord>) {
        _wordState.value = wordState.value!!.copy(savedWordsList = wordList)

    }

    private fun setRandomWords() {
        disposable.add(
            repository.getWords(50, 5)
                .subscribeOn(Schedulers.io())
                .map { array -> array.toList() }
                .toObservable()
                .flatMapIterable { list -> list }
                .map { item -> PwWord(item) }
                .toList()
                .doOnSubscribe { _wordApiState.postValue(WordApiState.LOADING) }
                .doOnSuccess { _wordApiState.postValue(WordApiState.DONE) }
                .subscribe (
                    {
                       _wordState.postValue(wordState.value!!.copy(randomWordsList = it))
                    },
                    {
                        _wordApiState.postValue(WordApiState.ERROR)
                    }
                )
        )
    }


    /* Symbols Fragment Area */

    private fun setFiveSymbols() {
        fiveSymbols = MutableLiveData(mutableListOf())

        val list = mutableListOf<String>()
        list.clear()
        list.addAll(allSymbols)
        repeat(5) {
            list.shuffled()
            val symbol = list[Random.nextInt(list.size)]
            fiveSymbols.value?.add(symbol)
            list.remove(symbol)
        }
    }

    fun reduceSymbolState(action: Action) {
        when(action) {
            is SymbolAction.getNewSymbols -> {
                setFiveSymbols()

            }
            else -> {}
        }

    }
}