package ru.justd.cryptobot.di

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.persistance.MongoStorageImpl
import ru.justd.cryptobot.persistance.Storage
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideUserPreferences(mongo: MongoDatabase): Storage = MongoStorageImpl(mongo)

    @Provides
    @Singleton
    fun provideMongo(): MongoDatabase = MongoClient("52.201.9.108").getDatabase("db")

}