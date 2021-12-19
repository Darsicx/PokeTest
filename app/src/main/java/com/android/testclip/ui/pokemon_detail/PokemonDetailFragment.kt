package com.android.testclip.ui.pokemon_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonDetailBinding
import com.android.testclip.di.ServiceLocator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: PokemonDetailViewModelFactory
    private val viewModel: PokemonDetailViewModel by viewModels { viewModelFactory }

    private val args: PokemonDetailFragmentArgs by navArgs()

    private var evolutiveChainUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory =
            ServiceLocator.providePokemonDetailViewModelFactory()
        viewModel.getPokemonsDetail(args.pokemonName)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonDetailBinding.bind(view)

        setupObservers()
        setupListeners()
        binding.gpPokemonDetailContainer.isVisible = false
    }

    private fun setupListeners() {
        binding.btnAbilities.setOnClickListener {
            val direction =
                PokemonDetailFragmentDirections.actionPokemonDetailFragmentToPokemonAbilityFragment(
                    pokemonName = args.pokemonName
                )
            findNavController().navigate(direction)
        }

        binding.btnEvolutiveChain.setOnClickListener {
            val direction =
                PokemonDetailFragmentDirections.actionPokemonDetailFragmentToPokemonEvolutionFragment(
                    evolutiveChainUrl = evolutiveChainUrl
                )
            findNavController().navigate(direction)
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonDetailState.collect { state ->
                    when (state) {
                        PokemonDetailViewModel.PokemonDetailState.DEFAULT -> Unit
                        PokemonDetailViewModel.PokemonDetailState.LOADING -> {
                            binding.pgPokemonDetail.isVisible = true
                        }
                        is PokemonDetailViewModel.PokemonDetailState.SUCCESS -> {
                            binding.pgPokemonDetail.isVisible = false
                            binding.gpPokemonDetailContainer.isVisible = true

                            binding.apply {
                                tvPokemonName.text = args.pokemonName
                                tvHappiness.text = state.pokemons.baseHappiness.toString()
                                tvRatio.text = state.pokemons.captureRate.toString()
                                tvEgg.text = state.pokemons.eggGroupDtos.joinToString { it.name }
                            }

                            evolutiveChainUrl = state.pokemons.evolutionChainDto.url
                        }
                        is PokemonDetailViewModel.PokemonDetailState.ERROR -> {
                            binding.pgPokemonDetail.isVisible = false
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