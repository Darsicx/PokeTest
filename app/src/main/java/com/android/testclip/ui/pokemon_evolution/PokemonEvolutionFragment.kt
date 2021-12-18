package com.android.testclip.ui.pokemon_evolution

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonEvolutionBinding

class PokemonEvolutionFragment : Fragment(R.layout.fragment_pokemon_evolution) {

    private var _binding: FragmentPokemonEvolutionBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonEvolutionBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}