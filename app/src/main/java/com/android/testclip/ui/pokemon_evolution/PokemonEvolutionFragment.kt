package com.android.testclip.ui.pokemon_evolution

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonEvolutionBinding
import com.android.testclip.di.ServiceLocator
import com.android.testclip.ui.pokemons.adapter.PokemonsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonEvolutionFragment : Fragment(R.layout.fragment_pokemon_evolution) {

    private var _binding: FragmentPokemonEvolutionBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: PokemonEvolutionViewModelFactory
    private var pokemonEvolutionAdapter: PokemonsAdapter? = null

    private val viewModel: PokemonEvolutionViewModel by viewModels { viewModelFactory }

    private val args: PokemonEvolutionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonEvolutionBinding.bind(view)

        viewModelFactory =
            ServiceLocator.providePokemonEvolutionViewModelFactory(this.requireContext())

        viewModel.getPokemonEvolutiveChain(args.evolutiveChainUrl)
        setupPokemonEvolutionAdapter()
        setupObservers()
    }

    private fun setupPokemonEvolutionAdapter() {
        binding.rvPokemonEvolution.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        pokemonEvolutionAdapter = PokemonsAdapter()
        binding.rvPokemonEvolution.adapter = pokemonEvolutionAdapter

        pokemonEvolutionAdapter?.setListener { name, position ->
            viewModel.savePokemonAsFavorite(name)
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonEvolutionState.collect { state ->
                    when (state) {
                        PokemonEvolutionViewModel.PokemonEvolutionState.DEFAULT -> Unit
                        PokemonEvolutionViewModel.PokemonEvolutionState.LOADING -> {
                            binding.pgPokemonEvolution.isVisible = true
                        }
                        is PokemonEvolutionViewModel.PokemonEvolutionState.SUCCESS -> {
                            binding.pgPokemonEvolution.isVisible = false
                            pokemonEvolutionAdapter?.updateData(state.pokemons)
                        }
                        is PokemonEvolutionViewModel.PokemonEvolutionState.ERROR -> {
                            binding.pgPokemonEvolution.isVisible = false
                            Toast.makeText(
                                requireContext(),
                                state.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonFavoriteState.collect { state ->
                    when (state) {
                        PokemonEvolutionViewModel.PokemonFavoriteState.DEFAULT -> Unit
                        PokemonEvolutionViewModel.PokemonFavoriteState.LOADING -> {
                            binding.pgPokemonEvolution.isVisible = true
                        }
                        is PokemonEvolutionViewModel.PokemonFavoriteState.SUCCESS -> {
                            binding.pgPokemonEvolution.isVisible = false
                            if (state.pokemon.pokemonName.isBlank()) {
                                Toast.makeText(
                                    requireContext(),
                                    "Pokemon no disponible",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val direction =
                                    PokemonEvolutionFragmentDirections.actionPokemonEvolutionFragmentToPokemonsFragment(
                                        state.pokemon
                                    )
                                findNavController().navigate(direction)
                            }

                        }
                        is PokemonEvolutionViewModel.PokemonFavoriteState.ERROR -> {
                            binding.pgPokemonEvolution.isVisible = false
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