package com.gaugustini.carddo.di

import com.gaugustini.carddo.database.ContactDao
import com.gaugustini.carddo.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideContactRepository(
        contactDao: ContactDao
    ): ContactRepository {
        return ContactRepository(contactDao)
    }

}
