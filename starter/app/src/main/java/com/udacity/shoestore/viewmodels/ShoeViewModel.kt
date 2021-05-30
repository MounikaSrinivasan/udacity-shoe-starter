package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private var _shoesList: MutableLiveData<MutableList<Shoe>> =
        MutableLiveData(initializeShoeList())

    private fun initializeShoeList(): MutableList<Shoe> {

        return mutableListOf(
            Shoe("Sneakers 5.0", 7.0, "Nike", "Nike Sneakers"),
            Shoe("Tennis", 8.0, "Adidas", "Adidas Tennis Shoes"),
            Shoe("Walking", 6.5, "Sketchers", "Sketchers Go Walk!")
        )
    }

    val shoesList: LiveData<MutableList<Shoe>> = _shoesList

    var shoeName: MutableLiveData<String> = MutableLiveData("")

    var shoeBrand: MutableLiveData<String> = MutableLiveData("")

    var shoeSize: MutableLiveData<String> = MutableLiveData("")

    var shoeDescription: MutableLiveData<String> = MutableLiveData("")

    fun submitClicked(index: Int) {
        if (index == -1) {
            shoesList.value?.add(
                Shoe(
                    shoeName.value ?: EMPTY_STRING,
                    shoeSize.value.toString().toDouble(),
                    shoeBrand.value ?: EMPTY_STRING,
                    shoeDescription.value ?: EMPTY_STRING
                )
            )
        } else {
            val shoe = shoesList.value?.get(index)
            if (shoe != null) {
                shoeName.value?.let {
                    shoe.name = it
                }
                shoeSize.value?.let {
                    shoe.size = it.toDouble()
                }
                shoeBrand.value?.let {
                    shoe.company = it
                }
                shoeDescription.value?.let {
                    shoe.description = it
                }
            }
        }
        clearShoeData()

    }

    fun cancelClicked() {
        clearShoeData()

    }

    fun clearShoeData() {
        shoeName.value = EMPTY_STRING
        shoeSize.value = EMPTY_STRING
        shoeBrand.value = EMPTY_STRING
        shoeDescription.value = EMPTY_STRING
    }

    companion object SHOE {
        const val EMPTY_STRING = ""
    }
}