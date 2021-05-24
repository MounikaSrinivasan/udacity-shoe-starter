package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainVM: ViewModel() {

    private val _toolbarState = MutableLiveData<Boolean>()
    var toolbarState: LiveData<Boolean> = _toolbarState

    private val _homeAsUpButton = MutableLiveData<Boolean>()
    var homeAsUpButton: LiveData<Boolean> = _homeAsUpButton

    fun updateToolbar(boolean: Boolean) {
        _toolbarState.postValue(boolean)
    }

    fun updateHomeAsUpDisplay(boolean: Boolean) {
        _homeAsUpButton.postValue(boolean)
    }

}
