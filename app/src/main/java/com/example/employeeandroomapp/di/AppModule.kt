package com.example.employeeandroomapp.di

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.employeeandroomapp.api.DirectoryApiService
import com.example.employeeandroomapp.database.DirectoryDao
import com.example.employeeandroomapp.database.DirectoryDatabase
import com.example.employeeandroomapp.R
import com.example.employeeandroomapp.repository.util.AppExecutors
import com.example.employeeandroomapp.utils.Constants
import com.example.employeeandroomapp.utils.Constants.Companion.BASE_URL
import com.example.employeeandroomapp.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule{
    //Retrofit
    @Singleton
    @Provides
    fun  provideRetrofitInstance(): DirectoryApiService
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build().create(DirectoryApiService::class.java);
    }

    // Glide RequestOptions
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
    }

    //Glide
    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application?,
        requestOptions: RequestOptions?
    ): RequestManager {
        return Glide.with(application!!)
            .setDefaultRequestOptions(requestOptions!!)
    }

    @Singleton
    @Provides
    fun provideDirectoryDataBase(app: Application): DirectoryDatabase
    {
        return Room.databaseBuilder(app, DirectoryDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideDirectoryDao(db: DirectoryDatabase) : DirectoryDao
    {
        return db.directoryDao()
    }

    @Singleton
    @Provides
    fun provideAppExecutos(): AppExecutors {
        return AppExecutors()
    }

}