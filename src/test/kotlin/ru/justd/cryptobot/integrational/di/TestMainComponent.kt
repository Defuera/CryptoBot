package ru.justd.cryptobot.di

import dagger.Component
import ru.justd.cryptobot.Main
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TestMainModule::class))
interface TestMainComponent {
    fun inject(main: Main)
}