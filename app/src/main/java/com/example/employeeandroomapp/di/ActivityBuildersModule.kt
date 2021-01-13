package com.example.employeeandroomapp.di

import com.example.employeeandroomapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule
{
    @ContributesAndroidInjector(modules = [MainFragmentsBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
