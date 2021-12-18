package com.android.testclip.ui.pokemons.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.testclip.databinding.ItemPokemonsBinding

class WordViewHolder(private val binding: ItemPokemonsBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBindItem(item: String, listener: ((name: String, position: Int) -> Unit)?, position: Int) {
        with(binding) {
            tvPokemonName.text = item

            root.setOnClickListener {
                //item.select = !item.select
                listener?.invoke(item, position)
            }
        }
    }
}