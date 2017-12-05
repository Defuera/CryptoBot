package ru.justd.cryptobot.di

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.persistance.MongoStorageImpl
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.StorageImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageModule constructor(
        val clientName: String,
        val feedbackStorage: FeedbackStorage
) {

    @Provides
    @Singleton
    fun provideMongo(): MongoDatabase = MongoClient("52.91.229.51").getDatabase(clientName)

    @Provides
    @Singleton
    fun provideUserPreferences(mongo: MongoDatabase, @Named("IsDebug") debug: Boolean): Storage =
            if (debug)
                StorageImpl()
            else
                MongoStorageImpl(mongo)

    @Provides
    @Singleton
    fun provideFeedbackStorage(): FeedbackStorage = feedbackStorage

}