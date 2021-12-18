package com.android.testclip.ui.pokemon_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonDetailBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}