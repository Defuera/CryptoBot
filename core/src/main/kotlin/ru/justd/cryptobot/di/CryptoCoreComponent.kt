package ru.justd.cryptobot.di

import dagger.Component
import ru.justd.cryptobot.CryptoCore
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainModule::class))
interface CryptoCoreComponent {
    fun inject(main: CryptoCore)
}