package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.utils.TimeManager
import ru.justd.cryptobot.utils.TimeManagerImpl
import ru.justd.cryptobot.utils.UuidGenerator
import ru.justd.cryptobot.utils.UuidGeneratorImpl
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