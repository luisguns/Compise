package br.com.compise

import android.app.Application
import br.com.compise.di.dataModule
import br.com.compise.di.domainModule
import br.com.compise.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CompiseApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CompiseApplication)
            modules(listOf (
                dataModule,
                presenterModule,
                domainModule
            ))
        }
    }
}