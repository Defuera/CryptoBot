package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.UserPreferencesImpl
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideUserPreferences(): UserPreferences = UserPreferencesImpl()

}