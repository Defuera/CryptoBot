package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.utils.TimeManager
import javax.inject.Named
import javax.inject.Singleton

@Module
class AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalytics(timeManager: TimeManager, @Named("IsDebug") debug: Boolean): Analytics =
            object : Analytics{
                override fun trackStart(channelId: String) {//todo
                }

                override fun trackPrice(channelId: String, exchange: String, base: String, target: String) {//todo
                }

                override fun trackSubscribe(channelId: String, exchange: String, base: String, target: String, period: String) {//todo
                }

                override fun trackUnsubscribe(channelId: String) {//todo
                }

                override fun trackAddressInfo(channelId: String) {//todo
                }

                override fun trackFeedback(channelId: String) {//todo
                }

            }

}