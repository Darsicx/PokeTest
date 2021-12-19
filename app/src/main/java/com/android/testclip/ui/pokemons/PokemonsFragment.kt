package com.android.testclip.ui.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testclip.R
import com.android.testclip.databinding.FragmentPokemonsBinding
import com.android.testclip.di.ServiceLocator
import com.android.testclip.ui.pokemons.adapter.PokemonsAdapter

class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {

    private var _binding: FragmentPokemonsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: PokemonsViewModelFactory
    private var pokemonsAdapter: PokemonsAdapter? = null

    private val viewModel: PokemonsViewModel by viewModels { viewModelFactory }

    private val args: PokemonsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ServiceLocator.providePokemonsViewModelFactory(this.requireContext())
        viewModel.getPokemonsData()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonsBinding.bind(view)

        setupPokemonsAdapter()
        setupObservers()
        deletePrefix()
    }

    private fun deletePrefix() {
        if (args.pokemonResponse != null) {
            viewModel.deletePrefix(args.pokemonResponse?.pokemonId!!)
        }
    }

    private fun setupPokemonsAdapter() {
        binding.rvPokemons.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        pokemonsAdapter = PokemonsAdapter()
        binding.rvPokemons.adapter = pokemonsAdapter

        pokemonsAdapter?.setListener { name, position ->
            val direction =
                PokemonsFragmentDirections.actionPokemonsFragmentToPokemonDetailFragment(pokemonName = name)
            findNavController().navigate(direction)
        }
    }

    private fun setupObservers() {
        viewModel.pokemonsState.observe(this, { state ->
            when (state) {
                PokemonsViewModel.PokemonsState.LOADING -> binding.pgPokemon.isVisible= true
                is PokemonsViewModel.PokemonsState.ERROR -> {
                    binding.pgPokemon.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        state.cause,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is PokemonsViewModel.PokemonsState.SUCCESS -> {
                    binding.pgPokemon.isVisible = false
                    pokemonsAdapter?.updateData(state.pokemons)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}