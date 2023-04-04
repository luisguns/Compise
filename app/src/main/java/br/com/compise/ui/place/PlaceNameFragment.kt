package br.com.compise.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.compise.R
import br.com.compise.databinding.FragmentPlaceNameBinding


class PlaceNameFragment : Fragment() {


    private var typeFragment: String? = null

    private var _binding: FragmentPlaceNameBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlaceNameBinding.inflate(inflater, container, false)
        _binding = binding

        if (typeFragment.isNullOrEmpty().not()){
            setupViewFromStep(typeFragment!!)
        } else {
            setupViewFromStep(PLACE_FRAGMENT_PLACE_NAME)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            typeFragment = it.getString(PLACE_FRAGMENT_KEY)
        }

    }

    private fun setupViewFromStep(step: String) {
        when {
            step == PLACE_FRAGMENT_PLACE_LOCATION ->  loadPlaceLocationView()
            step == PLACE_FRAGMENT_PLACE_NAME -> loadPlaceNameView()
        }
    }

    private fun loadPlaceLocationView() {
        binding.tvLabelPlace.text = "Localizaçâo do estabelecimento"
        binding.placeName.hint = "Local"
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_placeNameFragment_to_mainActivity)
        }
    }

    private fun loadPlaceNameView() {
        binding.tvLabelPlace.text = "Nome do estabelecimento"
        binding.placeName.hint = "Nome"
        binding.buttonNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(PLACE_FRAGMENT_KEY, PLACE_FRAGMENT_PLACE_LOCATION)
            findNavController().navigate(R.id.action_placeNameFragment_self,bundle)
        }
    }


    companion object {
        const val PLACE_FRAGMENT_PLACE_NAME = "PLACE_NAME"
        const val PLACE_FRAGMENT_PLACE_LOCATION = "PLACE_LOCATION"
        const val PLACE_FRAGMENT_KEY = "PLACE_FRAGMENT_KEY"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}