package ru.justd.cryptobot.di

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.utils.TimeManager
import ru.justd.cryptobot.utils.UuidGenerator
import javax.inject.Singleton

@Module
class UtilsModule {

    companion object {
        val timeManagerMock: TimeManager = mock()
        val uuidGeneratorMock: UuidGenerator = mock()
    }

    @Provides
    @Singleton
    fun provideDateManager(): TimeManager = timeManagerMock

    @Provides
    @Singleton
    fun provideUuidGenerator(): UuidGenerator = uuidGeneratorMock

}