package com.annakorowajczykapp.rickandmorty.di.module

import android.content.Context
import com.inverce.mod.v2.core.IMEx
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {
    @Provides
    fun provideContext(): Context = IMEx.context
}