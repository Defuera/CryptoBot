package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.analytics.KeenAnalytics
import utils.TimeManager
import javax.inject.Named
import javax.inject.Singleton

@Module
class AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalytics(timeManager: TimeManager, @Named("IsDebug") debug: Boolean): Analytics = KeenAnalytics(timeManager, debug)

}