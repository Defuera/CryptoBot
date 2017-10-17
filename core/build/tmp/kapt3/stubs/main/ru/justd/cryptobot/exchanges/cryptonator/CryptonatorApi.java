package ru.justd.cryptobot.exchanges.cryptonator;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0017\u00a8\u0006\r"}, d2 = {"Lru/justd/cryptobot/exchanges/cryptonator/CryptonatorApi;", "Lru/justd/cryptobot/exchanges/PollingExchange;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "getRateUrl", "", "base", "target", "parseResponseBody", "Lru/justd/cryptobot/exchanges/RateResponse;", "bodyString", "Companion", "core"})
public final class CryptonatorApi extends ru.justd.cryptobot.exchanges.PollingExchange {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME = "CRYPTONATOR";
    public static final ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getRateUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.exchanges.RateResponse parseResponseBody(@org.jetbrains.annotations.NotNull()
    java.lang.String bodyString, @org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) throws ru.justd.cryptobot.exchanges.exceptions.RequestFailed {
        return null;
    }
    
    public CryptonatorApi(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lru/justd/cryptobot/exchanges/cryptonator/CryptonatorApi$Companion;", "", "()V", "NAME", "", "core"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}