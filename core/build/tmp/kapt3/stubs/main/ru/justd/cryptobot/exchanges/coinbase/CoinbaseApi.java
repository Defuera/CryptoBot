package ru.justd.cryptobot.exchanges.coinbase;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0002\u000e\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0017J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016\u00a8\u0006\u0010"}, d2 = {"Lru/justd/cryptobot/exchanges/coinbase/CoinbaseApi;", "Lru/justd/cryptobot/exchanges/PollingExchange;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "getRateUrl", "", "base", "target", "parseResponseBody", "Lru/justd/cryptobot/exchanges/RateResponse;", "bodyString", "requestBuilder", "Lokhttp3/Request$Builder;", "Companion", "RateResponseEnvelope", "core"})
public final class CoinbaseApi extends ru.justd.cryptobot.exchanges.PollingExchange {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME = "COINBASE";
    public static final ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getRateUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public okhttp3.Request.Builder requestBuilder(@org.jetbrains.annotations.NotNull()
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
    
    public CoinbaseApi(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ*\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0018"}, d2 = {"Lru/justd/cryptobot/exchanges/coinbase/CoinbaseApi$RateResponseEnvelope;", "", "data", "Lru/justd/cryptobot/exchanges/RateResponse;", "errors", "", "Lru/justd/cryptobot/exchanges/coinbase/Error;", "(Lru/justd/cryptobot/exchanges/RateResponse;[Lru/justd/cryptobot/exchanges/coinbase/Error;)V", "getData", "()Lru/justd/cryptobot/exchanges/RateResponse;", "getErrors", "()[Lru/justd/cryptobot/exchanges/coinbase/Error;", "[Lru/justd/cryptobot/exchanges/coinbase/Error;", "component1", "component2", "copy", "(Lru/justd/cryptobot/exchanges/RateResponse;[Lru/justd/cryptobot/exchanges/coinbase/Error;)Lru/justd/cryptobot/exchanges/coinbase/CoinbaseApi$RateResponseEnvelope;", "equals", "", "other", "hashCode", "", "toString", "", "core"})
    static final class RateResponseEnvelope {
        @org.jetbrains.annotations.NotNull()
        private final ru.justd.cryptobot.exchanges.RateResponse data = null;
        @org.jetbrains.annotations.Nullable()
        private final ru.justd.cryptobot.exchanges.coinbase.Error[] errors = null;
        
        @org.jetbrains.annotations.NotNull()
        public final ru.justd.cryptobot.exchanges.RateResponse getData() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final ru.justd.cryptobot.exchanges.coinbase.Error[] getErrors() {
            return null;
        }
        
        public RateResponseEnvelope(@org.jetbrains.annotations.NotNull()
        ru.justd.cryptobot.exchanges.RateResponse data, @org.jetbrains.annotations.Nullable()
        ru.justd.cryptobot.exchanges.coinbase.Error[] errors) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ru.justd.cryptobot.exchanges.RateResponse component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final ru.justd.cryptobot.exchanges.coinbase.Error[] component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi.RateResponseEnvelope copy(@org.jetbrains.annotations.NotNull()
        ru.justd.cryptobot.exchanges.RateResponse data, @org.jetbrains.annotations.Nullable()
        ru.justd.cryptobot.exchanges.coinbase.Error[] errors) {
            return null;
        }
        
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(java.lang.Object p0) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lru/justd/cryptobot/exchanges/coinbase/CoinbaseApi$Companion;", "", "()V", "NAME", "", "core"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}