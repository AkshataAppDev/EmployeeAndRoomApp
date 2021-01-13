package com.example.employeeandroomapp.di

import com.example.employeeandroomapp.ui.employee.EmployeeDetailsFragment
import com.example.employeeandroomapp.ui.employee.EmployeeListFragment
import com.example.employeeandroomapp.ui.room.RoomListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBuildersModule
{
    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    abstract fun contributeEmployeeListFragment(): EmployeeListFragment

    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    abstract fun contributeEmployeeDetailsFragment(): EmployeeDetailsFragment

    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    abstract fun contributeRoomListFragment(): RoomListFragment

}