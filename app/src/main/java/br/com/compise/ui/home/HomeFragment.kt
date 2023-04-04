package br.com.compise.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import br.com.compise.databinding.FragmentHomeBinding
import br.com.compise.domain.utils.Resource
import br.com.compise.model.Cart
import br.com.compise.model.CartItem
import br.com.compise.ui.place.PlaceNameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var ITENS = mutableListOf<String>()


    val homeViewModel: HomeViewModel by viewModel()
    val placeNameViewModel: PlaceNameViewModel by viewModel()
    var cart: Cart? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_dropdown_item_1line, ITENS) }
        binding.adicionarComponent.tvProductName.setAdapter(adapter)
        binding.adicionarComponent.switchText.setQuantityCAllBackListen {quantity ->
            if(!binding.adicionarComponent.tvPrice.text.isNullOrEmpty()){
                changePrice(quantity, binding)
            }
        }
        binding.adicionarComponent.addButton.setOnClickListener {
            addItemCart(cart , CartItem(binding.adicionarComponent.tvProductName.text.toString(),
                binding.adicionarComponent.tvPrice.text.toString().toDouble(),binding.adicionarComponent.switchText.getQuantity()))
        }
        binding.adicionarComponent.tvPrice.doAfterTextChanged {
            if (!it.toString().isNullOrEmpty()){
            changePrice(binding.adicionarComponent.switchText.getQuantity(), binding)
            } else {
                binding.reviewMarket.tvValorItem.text = "R$: 0"
            }
        }

        placeNameViewModel.getCart()
        setObservers()

        getAllItemSection()

        return root
    }

    private fun setObservers() {
        placeNameViewModel.cartResultLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    cart = it.data
                }
                is Resource.Error -> {

                }
                else -> {

                }
            }
        }

        homeViewModel.myLiveData.observe(viewLifecycleOwner) { list ->
            when (list) {
                is Resource.Success -> {
                    ITENS.clear()
                    list.data?.distinctBy { it?.name }?.forEach { cartItem ->
                        cartItem?.name?.let { ITENS.add(it) }
                    }
                }
                is Resource.Error -> {  }//doNothing
                is Resource.Loading -> {  }//doNothing
            }
        }
    }


    private fun changePrice(
        quantity: Int,
        binding: FragmentHomeBinding
    ) {
        val totalPrice = quantity *
                binding.adicionarComponent.tvPrice.text.toString().toDouble()
        binding.reviewMarket.tvValorItem.text = "R$: %.2f".format(totalPrice)
    }

    fun addItemCart(cart: Cart?, cartItem: CartItem) {
        homeViewModel.addCartItem(cart, cartItem)
    }

    private fun getAllItemSection() {
        homeViewModel.getAllICartItem()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}