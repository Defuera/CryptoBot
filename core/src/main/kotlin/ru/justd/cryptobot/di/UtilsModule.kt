package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import utils.TimeManager
import utils.TimeManagerImpl
import utils.UuidGenerator
import utils.UuidGeneratorImpl
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideDateManager(): TimeManager = TimeManagerImpl

    @Provides
    @Singleton
    fun provideUuidGenerator(): UuidGenerator = UuidGeneratorImpl

}