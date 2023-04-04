package br.com.compise.domain.repository

import br.com.compise.domain.utils.Resource
import br.com.compise.model.Cart
import br.com.compise.model.CartItem
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun saveItemToCart(cart: Cart?, cartItem: CartItem): Flow<Resource<DocumentReference>>

    suspend fun getCartSection(cart: Cart?): Flow<Resource<Cart>>

    suspend fun getAllCartItem(): Flow<Resource<MutableList<CartItem?>>>
}