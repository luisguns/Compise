package br.com.compise.model

data class CartItem(
    val name: String ="",
    val price: Double = 0.0,
    val quantity: Int = 0
) {
    constructor() : this("", 0.0,0)
}
