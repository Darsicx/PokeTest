package com.android.testclip.ui.pokemon_ability

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonAbilitiesBinding
import com.android.testclip.di.ServiceLocator
import com.android.testclip.ui.pokemons.adapter.PokemonsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonAbilityFragment : Fragment(R.layout.fragment_pokemon_abilities) {

    private var _binding: FragmentPokemonAbilitiesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: PokemonAbilityViewModelFactory
    private val viewModel: PokemonAbilityViewModel by viewModels { viewModelFactory }

    private val args: PokemonAbilityFragmentArgs by navArgs()

    private var pokemonAbilitiesAdapter: PokemonsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonAbilitiesBinding.bind(view)

        viewModelFactory =
            ServiceLocator.providePokemonAbilityViewModelFactory()

        setupObservers()
        setupPokemonAbilitiesAdapter()
        viewModel.getPokemonAbilities(args.pokemonName)

    }

    private fun setupPokemonAbilitiesAdapter() {
        binding.rvPokemonAbilities.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        pokemonAbilitiesAdapter = PokemonsAdapter()
        binding.rvPokemonAbilities.adapter = pokemonAbilitiesAdapter
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonAbilityState.collect { state ->
                    when (state) {
                        PokemonAbilityViewModel.PokemonAbilityState.DEFAULT -> Unit
                        PokemonAbilityViewModel.PokemonAbilityState.LOADING -> {
                            Toast.makeText(
                                requireContext(),
                                "Cargando",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is PokemonAbilityViewModel.PokemonAbilityState.SUCCESS -> {
                            binding.apply {
                                tvPokemonName.text = args.pokemonName
                                tvPokemonName.isVisible = true
                            }

                            pokemonAbilitiesAdapter?.updateData(state.pokemonAbility)
                        }
                        is PokemonAbilityViewModel.PokemonAbilityState.ERROR -> {
                            Toast.makeText(
                                requireContext(),
                                state.cause,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}