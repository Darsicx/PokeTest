package com.android.testclip.ui.pokemon_ability

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonAbilitiesBinding

class PokemonAbilityFragment : Fragment(R.layout.fragment_pokemon_abilities) {

    private var _binding: FragmentPokemonAbilitiesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonAbilitiesBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}