package ru.justd.cryptobot.di;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lru/justd/cryptobot/di/MainComponent;", "", "inject", "", "main", "Lru/justd/cryptobot/TelegramCryptAdviser;", "telegram"})
@dagger.Component(modules = {ru.justd.cryptobot.di.MainModule.class})
@javax.inject.Singleton()
public abstract interface MainComponent {
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.TelegramCryptAdviser main);
}