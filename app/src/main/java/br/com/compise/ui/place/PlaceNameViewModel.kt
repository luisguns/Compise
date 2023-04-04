package br.com.compise.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.compise.domain.utils.Resource
import br.com.compise.model.Cart
import com.google.firebase.firestore.FirebaseFirestore

class PlaceNameViewModel() : ViewModel() {

    val cartResultLiveData = MutableLiveData<Resource<Cart?>>()


    fun getCart() {
        val snapShotListen = FirebaseFirestore.getInstance().collection("cartsection")
            .get()
            .addOnSuccessListener { snapShot ->
                var myDocument: Cart? = null
                if (snapShot != null) {
                    for (document in snapShot) {
                        myDocument = document.toObject(Cart::class.java)
                    }
                    cartResultLiveData.postValue(Resource.Success(myDocument))
                }
            }
    }


}