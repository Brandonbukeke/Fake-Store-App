package com.bukeke.fakestore.hilt

import com.bukeke.fakestore.redux.ApplicationState
import com.bukeke.fakestore.redux.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationStoreModule {
    @Provides
    @Singleton
    fun providesApplicationStateStore(): Store<ApplicationState>{
        return Store(ApplicationState())
    }
}