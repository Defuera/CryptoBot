package ru.justd.cryptobot.di

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import ru.justd.cryptobot.persistance.Storage
import javax.inject.Singleton

@Module
class StorageModule(debug : Boolean) {

    companion object {
        val storageMock = mock(Storage::class.java)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(mongo: MongoDatabase) = storageMock

    @Provides
    @Singleton
    fun provideMongo() = MongoClient().getDatabase("preferences")

}