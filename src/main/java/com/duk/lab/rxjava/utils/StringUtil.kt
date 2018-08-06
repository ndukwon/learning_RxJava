package com.duk.lab.rxjava.utils

class StringUtil {
    companion object {
        private val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        @JvmStatic
        fun numberToCharacter(number: Int): String {
            val c = ALPHABET.get(number % ALPHABET.length)
            return Character.toString(c)
        }
    }
}