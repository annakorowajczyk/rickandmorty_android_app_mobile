package com.annakorowajczykapp.rickandmorty.content.presentation.characterDetails

import androidx.lifecycle.MutableLiveData
import com.annakorowajczykapp.rickandmorty.content.base.BaseViewModel
import com.annakorowajczykapp.rickandmorty.di.Injector
import com.annakorowajczykapp.rickandmorty.model.SingleCharacter
import com.annakorowajczykapp.rickandmorty.utils.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterDetailsViewModel : BaseViewModel() {

    var characterInfo = MutableLiveData<SingleCharacter>()
    var errors = MutableLiveData<Throwable>()

    @Inject
    lateinit var api: ApiService

    init {
        Injector.component.inject(this)
    }

    fun getCharacterDetails(id: Int) {
        rxDisposer.add(api.getSingleCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { characterInfo.postValue(it) },
                { errors.postValue(it) }
            )

        )
    }

}
