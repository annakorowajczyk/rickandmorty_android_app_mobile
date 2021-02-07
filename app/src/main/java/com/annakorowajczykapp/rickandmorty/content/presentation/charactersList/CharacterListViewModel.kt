package com.annakorowajczykapp.rickandmorty.content.presentation.charactersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.annakorowajczykapp.rickandmorty.R
import com.annakorowajczykapp.rickandmorty.content.base.BaseViewModel
import com.annakorowajczykapp.rickandmorty.di.Injector
import com.annakorowajczykapp.rickandmorty.model.Character
import com.annakorowajczykapp.rickandmorty.model.ResultsListData
import com.annakorowajczykapp.rickandmorty.utils.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterListViewModel : BaseViewModel() {

    @Inject
    lateinit var api: ApiService

//    private val errorInfo: MutableLiveData<Int> = MutableLiveData()
//    var resultListData: MutableLiveData<List<ResultsListData>> = MutableLiveData()
    private var pageMutable: MutableLiveData<Int> = MutableLiveData(0)

    val page: LiveData<Int>
        get() = pageMutable

    init {
        Injector.component.inject(this)
//        getCharacterList(1)
    }

    fun getCharacters(page: Int): Observable<Character> {
        return page.let {
            api.getCharactersList(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun incrementPage() {
        pageMutable.postValue(page.value?.plus(1))
    }

//    fun getCharacterList(page: Int) {
//        rxDisposer.add(api.getCharactersList(page)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { getCharactersListSuccess(it) },
//                { getCharactersListFailure() }
//            )
//
//        )
//    }

//    private fun getCharactersListSuccess(lists: Character) {
//        lists.resultList.let {
//            resultListData.postValue(it)
//        }
//    }
//
//    private fun getCharactersListFailure() {
//        errorInfo.value = R.string.failure
//    }

//    override fun onCleared() {
//        super.onCleared()
//        subscription.dispose()
//    }

}