package br.com.compise.data

import br.com.compise.domain.repository.CartRepository
import br.com.compise.domain.utils.Resource

import br.com.compise.model.Cart
import br.com.compise.model.CartItem
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import java.util.Date

class CartRepositoryImp constructor(val db: FirebaseFirestore) : CartRepository {


    override suspend fun saveItemToCart(cart: Cart?, cartItem: CartItem) =
        callbackFlow<Resource<DocumentReference>> {
            cart?.id.let { cartId ->
                if (cartId.isNullOrEmpty().not()) {
                    val snapshot = db.collection("cartsection").document(cartId!!)
                        .collection("ItemsSection")
                        .add(cartItem).await()

                    trySend(Resource.Success(snapshot))

                }
            }
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    override suspend fun getCartSection(cart: Cart?) = flow<Resource<Cart>> {
        if (cart?.id.isNullOrEmpty()) {
            cart?.id = Date().time.toString()
            db.collection("cartsection").document(cart?.id.toString()).set(cart!!).await()
            emit(Resource.Success(cart))
        } else {
            cart?.let {emit(Resource.Success(it))}
        }

    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllCartItem(): Flow<Resource<MutableList<CartItem?>>> = callbackFlow {
        val snapShotListen = db.collectionGroup("ItemsSection")
            .addSnapshotListener { snapShot, e ->
                val list = mutableListOf<CartItem?>()
                val response = if (snapShot != null) {
                    for (document in snapShot.documents) {
                        document?.let {
                            list.add(it.toObject(CartItem::class.java))
                        }
                    }
                    Resource.Success(list)
                } else {
                    Resource.Error("InternalError")
                }

                trySend(response)
            }
        awaitClose {
            snapShotListen.remove()
        }
    }


}
