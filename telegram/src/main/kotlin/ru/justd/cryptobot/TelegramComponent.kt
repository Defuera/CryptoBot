package ru.justd.cryptobot

import dagger.Component
import ru.justd.cryptobot.di.MainModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainModule::class))
interface TelegramComponent {
    fun inject(main: TelegramMessenger)
}