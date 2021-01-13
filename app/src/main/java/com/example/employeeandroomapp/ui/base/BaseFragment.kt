package com.example.employeeandroomapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.employeeandroomapp.di.ViewModelProviderFactory
import com.example.employeeandroomapp.repository.util.AppExecutors
import com.example.employeeandroomapp.ui.viewmodel.MainViewModel
import dagger.android.support.DaggerFragment
import java.lang.Exception
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment()
{
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mainViewModel : MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = activity?.run {
            ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")
    }
}