package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import ru.justd.cryptobot.UserPreferences
import javax.inject.Singleton

@Module
class StorageModule {

    companion object {
        val userPreferences = mock(UserPreferences::class.java)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(): UserPreferences = userPreferences

}