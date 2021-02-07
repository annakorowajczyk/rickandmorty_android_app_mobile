package com.annakorowajczykapp.rickandmorty.content.presentation.charactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.annakorowajczykapp.rickandmorty.R
import com.annakorowajczykapp.rickandmorty.model.ResultsListData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private var characterListData: List<ResultsListData> = listOf()

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
        const val CHARACTER_NAME = "CHARACTER_NAME"
    }

    fun setData(dataList: List<ResultsListData>) {
        this.characterListData = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characterListData.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characterListData[position]
        holder.itemView.character_name.text = item.name

        Glide.with(holder.itemView)
            .load(item.image)
            .transform(RoundedCorners(5))
            .into(holder.itemView.character_image)

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(CHARACTER_ID, item.id)
            bundle.putString(CHARACTER_NAME, item.name)
            Navigation.createNavigateOnClickListener(R.id.characterDetailsFragment, bundle)
                .onClick(it)
        }

    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}