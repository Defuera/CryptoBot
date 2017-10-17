package ru.justd.cryptobot.exchanges;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\b"}, d2 = {"Lru/justd/cryptobot/exchanges/ExchangeFacade;", "", "getRate", "Lru/justd/cryptobot/exchanges/RateResponse;", "base", "", "target", "exchangeApiCode", "core"})
public abstract interface ExchangeFacade {
    
    @org.jetbrains.annotations.NotNull()
    public abstract ru.justd.cryptobot.exchanges.RateResponse getRate(@org.jetbrains.annotations.Nullable()
    java.lang.String base, @org.jetbrains.annotations.Nullable()
    java.lang.String target, @org.jetbrains.annotations.Nullable()
    java.lang.String exchangeApiCode);
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 3)
    public final class DefaultImpls {
    }
}