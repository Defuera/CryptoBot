package ru.justd.cryptobot;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J&\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\'J\b\u0010\f\u001a\u00020\u0003H&\u00a8\u0006\r"}, d2 = {"Lru/justd/cryptobot/UserPreferences;", "", "baseCurrency", "", "exchangeApi", "locale", "Ljava/util/Locale;", "storeSubscription", "", "base", "target", "exchange", "targetCurrency", "core"})
public abstract interface UserPreferences {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String baseCurrency();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String targetCurrency();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String exchangeApi();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Locale locale();
    
    public abstract void storeSubscription(@org.jetbrains.annotations.Nullable()
    java.lang.String base, @org.jetbrains.annotations.Nullable()
    java.lang.String target, @org.jetbrains.annotations.Nullable()
    java.lang.String exchange) throws ru.justd.cryptobot.handler.subscribe.StorageException;
}