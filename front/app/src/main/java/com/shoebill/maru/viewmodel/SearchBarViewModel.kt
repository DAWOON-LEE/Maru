package com.shoebill.maru.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchBarViewModel @Inject constructor() : ViewModel() {
    private val _keyword = MutableLiveData<String>()

    val keyword: LiveData<String> get() = _keyword

    fun updateKeyword(value: String) {
        Log.d("SearchBarViewModel", value)
        _keyword.value = value
    }
}