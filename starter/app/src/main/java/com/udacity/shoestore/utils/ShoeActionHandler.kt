package com.udacity.shoestore.utils

import android.view.View

interface ShoeActionHandler {
    fun submitClicked(view:View)
    fun cancelClicked(view:View)
}
