package ru.justd.cryptobot.di;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\b\u0010\u0010\u001a\u00020\u000fH\u0007\u00a8\u0006\u0011"}, d2 = {"Lru/justd/cryptobot/di/MainModule;", "", "()V", "provideCommandHandlerFacade", "Lru/justd/cryptobot/handler/CommandHandlerFacade;", "exchangeFacade", "Lru/justd/cryptobot/exchanges/ExchangeFacade;", "userPreferences", "Lru/justd/cryptobot/UserPreferences;", "provideMessageReceiver", "Lru/justd/cryptobot/messenger/MessageReceiver;", "commandHandlerFacade", "provideMessageSender", "Lru/justd/cryptobot/messenger/MessageSender;", "telegramBot", "Lcom/pengrad/telegrambot/TelegramBot;", "provideTelegramBotAdapter", "telegram"})
@dagger.Module(includes = {ru.justd.cryptobot.di.ExchangeApiModule.class, ru.justd.cryptobot.di.StorageModule.class})
public final class MainModule {
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.pengrad.telegrambot.TelegramBot provideTelegramBotAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.handler.CommandHandlerFacade provideCommandHandlerFacade(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.exchanges.ExchangeFacade exchangeFacade, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.UserPreferences userPreferences) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.messenger.MessageReceiver provideMessageReceiver(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.handler.CommandHandlerFacade commandHandlerFacade) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.messenger.MessageSender provideMessageSender(@org.jetbrains.annotations.NotNull()
    com.pengrad.telegrambot.TelegramBot telegramBot) {
        return null;
    }
    
    public MainModule() {
        super();
    }
}