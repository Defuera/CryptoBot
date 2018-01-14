package ru.justd.cryptobot.di

import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
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
    fun provideUserPreferences(@Named("IsDebug") debug: Boolean): Storage {
        return if (debug) {
            StorageImpl()
        } else {
            val credential = MongoCredential.createCredential(
                    CoreConfig.MONGO_USER,
                    clientName,
                    CoreConfig.MONGO_PASS.toCharArray()
            )
            val mongoClient = MongoClient(ServerAddress(CoreConfig.MONGO_ADDRESS), mutableListOf<MongoCredential>(credential))
            MongoStorageImpl(mongoClient.getDatabase(clientName))
        }
    }

    @Provides
    @Singleton
    fun provideFeedbackStorage(): FeedbackStorage = feedbackStorage

}