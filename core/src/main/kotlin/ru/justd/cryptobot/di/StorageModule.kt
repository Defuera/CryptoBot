package ru.justd.cryptobot.di

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.persistance.MongoStorageImpl
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.StorageImpl
import javax.inject.Singleton

@Module
class StorageModule constructor(val clientName : String, val debug: Boolean) {

    @Provides
    @Singleton
    fun provideMongo(): MongoDatabase = MongoClient("52.91.229.51").getDatabase(clientName)

    @Provides
    @Singleton
    fun provideUserPreferences(mongo: MongoDatabase): Storage =
            if (debug)
                StorageImpl()
            else
                MongoStorageImpl(mongo)

}