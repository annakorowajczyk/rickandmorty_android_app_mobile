package com.annakorowajczykapp.rickandmorty.content.base

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    var actions: MainActivityInteractions? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        actions = context as? MainActivityInteractions
    }

    override fun onDetach() {
        super.onDetach()
        actions = null
    }

    protected fun setTitle(title: String) {
        if(activity != null) {
            activity?.title = title
        }
    }
}