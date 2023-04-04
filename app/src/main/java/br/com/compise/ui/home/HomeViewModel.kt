package br.com.compise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.compise.domain.UseCase.CartUseCase
import br.com.compise.domain.utils.Resource
import br.com.compise.model.Cart
import br.com.compise.model.CartItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel constructor(val cartUseCase: CartUseCase) : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val myLiveData = MutableLiveData<Resource<MutableList<CartItem?>>>()

    fun addCartItem(cart: Cart?, cartItem: CartItem) {
        viewModelScope.launch {
            cartUseCase.addItemCart(cart, cartItem).collect()
        }
    }

    fun getAllICartItem() {
        viewModelScope.launch {
            cartUseCase.getAllCartItem().collect() {
                myLiveData.postValue(it)
            }
        }
    }

}