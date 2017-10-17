package ru.justd.cryptobot.di;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J8\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u00042\b\b\u0001\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0012\u001a\u00020\u0006H\u0007\u00a8\u0006\u0013"}, d2 = {"Lru/justd/cryptobot/di/ExchangeApiModule;", "", "()V", "provideBitfinexApi", "Lru/justd/cryptobot/exchanges/ExchangeApi;", "okHttpClient", "Lokhttp3/OkHttpClient;", "provideCoinbaseApi", "provideCryptonator", "provideExchangeFacade", "Lru/justd/cryptobot/exchanges/ExchangeFacade;", "gdaxApi", "coinbaseApi", "cryptonatorApi", "bitfinexApi", "userPreferences", "Lru/justd/cryptobot/UserPreferences;", "provideGdaxApi", "provideOkHttpClient", "telegram"})
@dagger.Module()
public final class ExchangeApiModule {
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final okhttp3.OkHttpClient provideOkHttpClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GDAX")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.exchanges.ExchangeApi provideGdaxApi(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "COINBASE")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.exchanges.ExchangeApi provideCoinbaseApi(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CRYPTONATOR")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.exchanges.ExchangeApi provideCryptonator(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BITFINEX")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.exchanges.ExchangeApi provideBitfinexApi(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final ru.justd.cryptobot.exchanges.ExchangeFacade provideExchangeFacade(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GDAX")
    ru.justd.cryptobot.exchanges.ExchangeApi gdaxApi, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "COINBASE")
    ru.justd.cryptobot.exchanges.ExchangeApi coinbaseApi, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CRYPTONATOR")
    ru.justd.cryptobot.exchanges.ExchangeApi cryptonatorApi, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BITFINEX")
    ru.justd.cryptobot.exchanges.ExchangeApi bitfinexApi, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.UserPreferences userPreferences) {
        return null;
    }
    
    public ExchangeApiModule() {
        super();
    }
}