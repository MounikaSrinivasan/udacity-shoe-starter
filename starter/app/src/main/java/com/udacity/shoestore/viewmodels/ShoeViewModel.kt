package com.udacity.shoestore.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.utils.ShoeActionHandler

class ShoeViewModel : ViewModel() {

    private  var _shoesList: MutableLiveData<MutableList<Shoe>> = MutableLiveData(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>> = _shoesList


   // private lateinit var _shoeName: MutableLiveData<String>
   // var shoeName: LiveData<String> = _shoeName
    var shoeName: MutableLiveData<String> = MutableLiveData("")

    //  private lateinit var _shoeBrand: MutableLiveData<String>
  //  var shoeBrand: LiveData<String> = _shoeBrand
     var shoeBrand: MutableLiveData<String> = MutableLiveData("")

  //  private lateinit var _shoeSize: MutableLiveData<String>
  //  var shoeSize: LiveData<String> = _shoeSize
     var shoeSize: MutableLiveData<String> = MutableLiveData("")


    fun submitClicked(view: View, shoe:Shoe) {
        shoesList.value?.add(shoe)
        view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoesListFragment)
    }

    fun cancelClicked(view: View) {
        view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoesListFragment)
    }
}