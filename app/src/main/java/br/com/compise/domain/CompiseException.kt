package br.com.compise.domain

sealed class CompiseException: Throwable() {
    class CartSaveException: Throwable()
}
