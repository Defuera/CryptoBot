package ru.justd.cryptobot.exchanges;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B5\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0003J&\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0017R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lru/justd/cryptobot/exchanges/ExchangeFacadeImpl;", "Lru/justd/cryptobot/exchanges/ExchangeFacade;", "gdaxApi", "Lru/justd/cryptobot/exchanges/ExchangeApi;", "coinbaseApi", "cryptonatorApi", "bitfinexApi", "preferences", "Lru/justd/cryptobot/UserPreferences;", "(Lru/justd/cryptobot/exchanges/ExchangeApi;Lru/justd/cryptobot/exchanges/ExchangeApi;Lru/justd/cryptobot/exchanges/ExchangeApi;Lru/justd/cryptobot/exchanges/ExchangeApi;Lru/justd/cryptobot/UserPreferences;)V", "getApi", "exchangeApiCode", "", "getRate", "Lru/justd/cryptobot/exchanges/RateResponse;", "base", "target", "core"})
public final class ExchangeFacadeImpl implements ru.justd.cryptobot.exchanges.ExchangeFacade {
    private final ru.justd.cryptobot.exchanges.ExchangeApi gdaxApi = null;
    private final ru.justd.cryptobot.exchanges.ExchangeApi coinbaseApi = null;
    private final ru.justd.cryptobot.exchanges.ExchangeApi cryptonatorApi = null;
    private final ru.justd.cryptobot.exchanges.ExchangeApi bitfinexApi = null;
    private final ru.justd.cryptobot.UserPreferences preferences = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.exchanges.RateResponse getRate(@org.jetbrains.annotations.Nullable()
    java.lang.String base, @org.jetbrains.annotations.Nullable()
    java.lang.String target, @org.jetbrains.annotations.Nullable()
    java.lang.String exchangeApiCode) throws ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported {
        return null;
    }
    
    private final ru.justd.cryptobot.exchanges.ExchangeApi getApi(java.lang.String exchangeApiCode) throws ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported {
        return null;
    }
    
    public ExchangeFacadeImpl(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GDAX")
    ru.justd.cryptobot.exchanges.ExchangeApi gdaxApi, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "COINBASE")
    ru.justd.cryptobot.exchanges.ExchangeApi coinbaseApi, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CRYPTONATOR")
    ru.justd.cryptobot.exchanges.ExchangeApi cryptonatorApi, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BITFINEX")
    ru.justd.cryptobot.exchanges.ExchangeApi bitfinexApi, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.UserPreferences preferences) {
        super();
    }
}