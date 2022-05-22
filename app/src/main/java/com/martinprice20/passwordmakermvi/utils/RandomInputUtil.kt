package com.martinprice20.passwordmakermvi.utils

class RandomInputUtil {
    companion object {

        @JvmStatic
        fun getRandomNumber(): Int {
            return (0..99).random()
        }
    }

}