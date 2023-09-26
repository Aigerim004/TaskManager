package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)

    fun isUserShow(): Boolean {
        return pref.getBoolean(BOARD_KEY, false)
    }

    fun userShowed(){
        pref.edit().putBoolean(BOARD_KEY, true).apply()
    }

    companion object {
        const val PREF_KEY = "pref.key"
        const val BOARD_KEY = "board.key"
    }
}