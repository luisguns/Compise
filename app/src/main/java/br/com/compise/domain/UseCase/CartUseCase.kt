package br.com.compise.domain.UseCase

import br.com.compise.domain.utils.Resource
import br.com.compise.model.Cart
import br.com.compise.model.CartItem
import kotlinx.coroutines.flow.Flow


interface CartUseCase {

    fun saveCart(cart: Cart): Flow<Resource<Int>>

    suspend fun addItemCart(cart: Cart?, item: CartItem): Flow<Resource<String>>

    suspend fun getAllCartItem(): Flow<Resource<MutableList<CartItem?>>>
}