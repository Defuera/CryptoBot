package ru.justd.cryptobot.di

import dagger.Component
import ru.justd.cryptobot.Main
import ru.justd.cryptobot.handler.CommandHandlerFacade
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(main: Main)
    fun inject(commandHandlerFacade: CommandHandlerFacade)
}