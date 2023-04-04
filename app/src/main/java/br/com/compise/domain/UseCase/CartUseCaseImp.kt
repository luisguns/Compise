package br.com.compise.domain.UseCase

import br.com.compise.domain.repository.CartRepository
import br.com.compise.domain.utils.Resource
import br.com.compise.model.Cart
import br.com.compise.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartUseCaseImp constructor(val repository: CartRepository) : CartUseCase {
    override fun saveCart(cart: Cart): Flow<Resource<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun addItemCart(cart: Cart?, item: CartItem): Flow<Resource<String>> = flow {
        repository.getCartSection(cart).collect {
            when (it) {
                is Resource.Success -> {
                    repository.saveItemToCart(cart, item).collect() {
                        emit(Resource.Success("Success"))
                    }
                }

                is Resource.Error -> {
                    emit(Resource.Error(FAIL_REQUEST.toString()))
                }

                else -> {

                }
            }
        }
    }

    override suspend fun getAllCartItem(): Flow<Resource<MutableList<CartItem?>>> = flow {
        repository.getAllCartItem().collect() {
            emit(it)
        }
    }


    companion object {
        const val FAIL_REQUEST = -1
    }
}