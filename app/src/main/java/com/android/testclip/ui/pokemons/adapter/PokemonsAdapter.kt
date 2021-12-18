package com.android.testclip.ui.pokemons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.testclip.databinding.ItemPokemonsBinding

class PokemonsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var wordsList = listOf<String>()
    private var mListener: ((name: String, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            ItemPokemonsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return WordViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as WordViewHolder
        viewHolder.onBindItem(wordsList[position], mListener, position)
    }

    fun setListener(listener: (name: String, position: Int) -> Unit) {
        mListener = listener
    }

    fun updateData(data: List<String>) {
        wordsList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return wordsList.size
    }
}