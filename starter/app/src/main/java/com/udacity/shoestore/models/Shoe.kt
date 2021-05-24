package com.udacity.shoestore.models

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(@get:Bindable var name: String,
                @get:Bindable var size: Double,
                @get:Bindable var company: String,
                @get:Bindable var description: String,
                val images: List<String> = mutableListOf())  : BaseObservable(), Parcelable