package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.StorageImpl
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideUserPreferences(): Storage = StorageImpl(HashMap())

}