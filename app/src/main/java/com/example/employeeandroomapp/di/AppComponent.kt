package com.example.employeeandroomapp.di

import android.app.Application
import com.example.employeeandroomapp.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules=[AndroidInjectionModule::class,
ActivityBuildersModule::class,
AppModule::class,
ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<MyApplication>
{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder // bind app instance to app component
        fun build(): AppComponent
    }
}
