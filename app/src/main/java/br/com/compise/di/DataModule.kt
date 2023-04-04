package br.com.compise.di

import br.com.compise.data.CartRepositoryImp
import br.com.compise.domain.repository.CartRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module


val dataModule = module {

    factory<CartRepository> {
        CartRepositoryImp(FirebaseFirestore.getInstance())
    }
}