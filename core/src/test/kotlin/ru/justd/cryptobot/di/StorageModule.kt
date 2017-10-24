package ru.justd.cryptobot.di

import com.mongodb.MongoClient
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import ru.justd.cryptobot.persistance.Storage
import javax.inject.Singleton

@Module
class StorageModule {

    companion object {
        val storageMock = mock(Storage::class.java)
    }

    @Provides
    @Singleton
    fun provideUserPreferences() = storageMock

    @Provides
    @Singleton
    fun provideMongo() = MongoClient().getDatabase("preferences")

}