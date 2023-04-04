package br.com.compise.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.compise.databinding.SwitchTextComponentBinding


class SwitchText @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null,
                                           defStyle: Int = 0,
                                           defStyleRes: Int = 0): ConstraintLayout(context,attrs,defStyle,defStyleRes) {

    private val binding = SwitchTextComponentBinding.inflate(LayoutInflater.from(context),this,true)
    private var quantity: Int = 1
    private lateinit var callback: (Int) -> Unit
    private val buttonClick = AlphaAnimation(1f, 0.8f)
    init {
        updateQuantity()
        binding.run {
            btnAumentar.setOnClickListener {
                it.startAnimation(buttonClick)
                quantity += 1
                updateQuantity()
                callback(quantity)
            }

            btnDiminuir.setOnClickListener {
                it.startAnimation(buttonClick)
                if (quantity <= 0) return@setOnClickListener
                quantity -= 1
                updateQuantity()
                callback(quantity)
            }
        }
    }


    fun setQuantityCAllBackListen(listen: (Int) -> Unit) {
        callback = listen
    }

    private fun updateQuantity() {
        binding.tvQuantity.text = quantity.toString()
    }

    fun getQuantity(): Int = quantity
}