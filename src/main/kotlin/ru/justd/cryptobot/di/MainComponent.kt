package ru.justd.cryptobot.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {
}