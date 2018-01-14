package ru.justd.cryptobot.di

import com.mongodb.MongoClient
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.CoreConfig
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.persistance.MongoStorageImpl
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.StorageImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
class StorageModule constructor(
        private val clientName: String,
        private val feedbackStorage: FeedbackStorage
) {

    @Provides
    @Singleton
    fun provideUserPreferences(@Named("IsDebug") debug: Boolean): Storage =
            if (debug)
                StorageImpl()
            else
                MongoStorageImpl(MongoClient(CoreConfig.MONGO_ADDRESS).getDatabase(clientName))

    @Provides
    @Singleton
    fun provideFeedbackStorage(): FeedbackStorage = feedbackStorage

}