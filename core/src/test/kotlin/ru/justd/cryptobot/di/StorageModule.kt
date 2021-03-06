package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.persistance.Storage
import javax.inject.Named
import javax.inject.Singleton

@Module
@Suppress("UNUSED_PARAMETER")
class StorageModule(clientName: String, feedbackStorage: FeedbackStorage) {

    companion object {
        val storageMock = mock(Storage::class.java)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@Named("IsDebug") debug: Boolean) = storageMock

    @Provides
    @Singleton
    fun provideFeedbackStorage() = mock(FeedbackStorage::class.java)

}