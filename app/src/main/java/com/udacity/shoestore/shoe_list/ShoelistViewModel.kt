package com.udacity.shoestore.shoe_list

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.BR

class ShoelistViewModel : ViewModel() , Observable {
    private val propertyChangeRegistry = PropertyChangeRegistry()

    private val shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    @Bindable
    var shoe = Shoe()
        set(value) {
            if(value != field) {
                field = value
                propertyChangeRegistry.notifyChange(this, BR.shoe)
            }
        }

    fun getShoeLiveData(): LiveData<MutableList<Shoe>> = shoes

    fun addShoe(item: Shoe?) {
        item?.let {
            shoes.value?.add(item)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }
}