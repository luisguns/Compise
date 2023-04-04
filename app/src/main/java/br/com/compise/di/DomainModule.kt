package br.com.compise.di

import br.com.compise.domain.UseCase.CartUseCase
import br.com.compise.domain.UseCase.CartUseCaseImp
import org.koin.dsl.module

val domainModule = module {
    factory<CartUseCase> { CartUseCaseImp(get()) }
}