package ru.justd.cryptobot.exchanges;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\'J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lru/justd/cryptobot/exchanges/PollingExchange;", "Lru/justd/cryptobot/exchanges/ExchangeApi;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "getRate", "Lru/justd/cryptobot/exchanges/RateResponse;", "base", "", "target", "getRateUrl", "parseResponseBody", "bodyString", "requestBuilder", "Lokhttp3/Request$Builder;", "core"})
public abstract class PollingExchange implements ru.justd.cryptobot.exchanges.ExchangeApi {
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    private final okhttp3.OkHttpClient okHttpClient = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.gson.Gson getGson() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.exchanges.RateResponse getRate(@org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public okhttp3.Request.Builder requestBuilder(@org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getRateUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target);
    
    @org.jetbrains.annotations.NotNull()
    public abstract ru.justd.cryptobot.exchanges.RateResponse parseResponseBody(@org.jetbrains.annotations.NotNull()
    java.lang.String bodyString, @org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) throws ru.justd.cryptobot.exchanges.exceptions.RequestFailed;
    
    public PollingExchange(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        super();
    }
}