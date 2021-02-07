package com.annakorowajczykapp.rickandmorty.di.component

import com.annakorowajczykapp.rickandmorty.content.presentation.characterDetails.CharacterDetailsFragment
import com.annakorowajczykapp.rickandmorty.di.module.RestModule
import com.annakorowajczykapp.rickandmorty.content.presentation.characterDetails.CharacterDetailsViewModel
import com.annakorowajczykapp.rickandmorty.content.presentation.charactersList.CharacterListViewModel
import com.annakorowajczykapp.rickandmorty.di.module.AndroidModule
import dagger.Component

@Component(modules = [AndroidModule::class, RestModule::class])
interface AppComponent {
    fun inject(into: CharacterListViewModel)
    fun inject(into: CharacterDetailsViewModel)
    fun inject(into: CharacterDetailsFragment)
}