package com.example.employeeandroomapp.di

import androidx.lifecycle.ViewModel
import com.example.employeeandroomapp.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class) // mapping viewmodel into viewmodel key.
    abstract fun bindMainViewModel(listViewModel: MainViewModel): ViewModel
}