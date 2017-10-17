package ru.justd.cryptobot.handler;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lru/justd/cryptobot/handler/PriceCommandHandlerFactory;", "Lru/justd/cryptobot/handler/CommandHandlerFactory;", "Lru/justd/cryptobot/handler/PriceCommandHandler;", "()V", "exchangeFacade", "Lru/justd/cryptobot/exchanges/ExchangeFacade;", "getExchangeFacade", "()Lru/justd/cryptobot/exchanges/ExchangeFacade;", "setExchangeFacade", "(Lru/justd/cryptobot/exchanges/ExchangeFacade;)V", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "create", "retrieveArg", "index", "", "core"})
public final class PriceCommandHandlerFactory implements ru.justd.cryptobot.handler.CommandHandlerFactory<ru.justd.cryptobot.handler.PriceCommandHandler> {
    @org.jetbrains.annotations.NotNull()
    public ru.justd.cryptobot.exchanges.ExchangeFacade exchangeFacade;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String message;
    
    @org.jetbrains.annotations.NotNull()
    public final ru.justd.cryptobot.exchanges.ExchangeFacade getExchangeFacade() {
        return null;
    }
    
    public final void setExchangeFacade(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.exchanges.ExchangeFacade p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.handler.PriceCommandHandler create() {
        return null;
    }
    
    private final java.lang.String retrieveArg(int index) {
        return null;
    }
    
    public PriceCommandHandlerFactory() {
        super();
    }
}