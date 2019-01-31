package com.example.baseproject.ui.main.di

import com.example.baseproject.repository.Repository
import com.example.baseproject.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import com.example.baseproject.ui.main.MainActivityViewModel

@Module
class MainActivityModule {

    @Provides
    fun provideViewModel(repository: Repository, schedulerProvider: SchedulerProvider) = MainActivityViewModel(repository, schedulerProvider)
}