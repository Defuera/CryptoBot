package ru.justd.cryptobot.exchanges.exceptions;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lru/justd/cryptobot/exchanges/exceptions/ExchangeNotSupported;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "exchange", "", "(Ljava/lang/String;)V", "getExchange", "()Ljava/lang/String;", "core"})
public final class ExchangeNotSupported extends java.lang.RuntimeException {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String exchange = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExchange() {
        return null;
    }
    
    public ExchangeNotSupported(@org.jetbrains.annotations.NotNull()
    java.lang.String exchange) {
        super();
    }
}