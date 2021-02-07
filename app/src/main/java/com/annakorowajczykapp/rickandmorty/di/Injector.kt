package com.annakorowajczykapp.rickandmorty.di

import com.annakorowajczykapp.rickandmorty.di.component.DaggerAppComponent

object Injector {
    val component by lazy { DaggerAppComponent.create()}
}