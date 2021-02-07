package com.annakorowajczykapp.rickandmorty.content.presentation.characterDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annakorowajczykapp.rickandmorty.R
import com.annakorowajczykapp.rickandmorty.content.base.BaseFragment
import com.annakorowajczykapp.rickandmorty.content.presentation.charactersList.CharacterListAdapter.Companion.CHARACTER_ID
import com.annakorowajczykapp.rickandmorty.di.Injector
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.fragment_character_details.*
import javax.inject.Inject

class CharacterDetailsFragment : BaseFragment() {

    @Inject
    lateinit var baseContext: Context

    private lateinit var viewModel: CharacterDetailsViewModel
    private var characterId: Int ? = null
    private var characterName: String = ""

    init {
        Injector.component.inject(this)
    }

    companion object {
        const val CHARACTER_NAME = "CHARACTER_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)
        arguments?.let {
            characterId = arguments?.getInt(CHARACTER_ID) ?: 0
            characterName = arguments?.getString(CHARACTER_NAME) ?: ""
        }
        viewModel.getCharacterDetails(characterId ?: 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle("Character details")
        setObserver()
    }

    private fun setObserver() {
        viewModel.characterInfo.observe(viewLifecycleOwner, Observer {
            it?.let { character ->
                characterId
                character_name_tv.text = characterName
                gender_tv.text = character.gender
                life_tv.text = character.status
                origin_tv.text = character.origin.name
                current_location_tv.text = character.location.name
                Glide.with(baseContext)
                    .load(character.image)
                    .transform(RoundedCorners(5))
                    .into(character_poster)
            }

        })

        viewModel.errors.observe(viewLifecycleOwner, Observer {
            Toast.makeText(baseContext, it.message, Toast.LENGTH_SHORT).show()
        })
    }

}