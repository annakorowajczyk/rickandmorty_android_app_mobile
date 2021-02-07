package com.annakorowajczykapp.rickandmorty.content.presentation.charactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annakorowajczykapp.rickandmorty.R
import com.annakorowajczykapp.rickandmorty.content.base.BaseFragment
import com.annakorowajczykapp.rickandmorty.model.Character
import com.annakorowajczykapp.rickandmorty.model.ResultsListData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : BaseFragment() {

    private lateinit var viewModel: CharacterListViewModel
    private val characterAdapter by lazy { CharacterListAdapter() }
    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        setTitle("Characters")
        prepareRecyclerView()
        fetchCharacters()
        setListeners()
//        observeData()
    }

    private fun setListeners() {
        character_recycler_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!character_recycler_list.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    viewModel.incrementPage()
                }
            }
        })
    }

    private fun prepareRecyclerView() {
        character_recycler_list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = characterAdapter
        }
    }

    private fun fetchCharacters() {
        viewModel.page.observe(viewLifecycleOwner, Observer { page ->
            val characterList = ArrayList<ResultsListData>()
            viewModel.getCharacters(page).subscribe(
                object : io.reactivex.Observer<Character> {
                    override fun onComplete() {
                        characterAdapter.setData(characterList)
                    }
                    override fun onSubscribe(dispose: Disposable) {
                        compositeDisposable.add(dispose)
                    }
                    override fun onNext(char: Character) {
                        char.resultList.forEach { character ->
                            character.let { characterList.add(it) }
                        }
                    }
                    override fun onError(e: Throwable) {
                        Toast.makeText(context, getString(R.string.failure), Toast.LENGTH_SHORT).show()
                    }
                }
            )
        })

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

//    private fun observeData() {
//        viewModel.resultListData.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                characterAdapter.setData(it)
//            }
//        })
//    }


}