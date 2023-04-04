package br.com.compise.model

data class Cart(
    var id: String? = null,
    var placeName: String,
    var ItemsSection: List<CartItem>? = null
) {
    constructor() : this(placeName = "")
}

const val CART_SECTION = "CartSection"
const val EMPTY_SECTION = "EMPTY_SECTION"
