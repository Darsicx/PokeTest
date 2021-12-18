package com.android.testclip.ui.pokemons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonsBinding

class PokemonsFragment: Fragment(R.layout.fragment_pokemons) {

    private var _binding: FragmentPokemonsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonsBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}