package ru.justd.cryptobot.handler;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lru/justd/cryptobot/handler/PriceCommandHandler;", "Lru/justd/cryptobot/handler/CommandHandler;", "exchangeFacade", "Lru/justd/cryptobot/exchanges/ExchangeFacade;", "base", "", "target", "exchange", "(Lru/justd/cryptobot/exchanges/ExchangeFacade;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "responseMessage", "Lru/justd/cryptobot/messaging/model/OutgoingMessage;", "core"})
public final class PriceCommandHandler implements ru.justd.cryptobot.handler.CommandHandler {
    private final ru.justd.cryptobot.exchanges.ExchangeFacade exchangeFacade = null;
    private final java.lang.String base = null;
    private final java.lang.String target = null;
    private final java.lang.String exchange = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.messaging.model.OutgoingMessage responseMessage() {
        return null;
    }
    
    public PriceCommandHandler(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.exchanges.ExchangeFacade exchangeFacade, @org.jetbrains.annotations.Nullable()
    java.lang.String base, @org.jetbrains.annotations.Nullable()
    java.lang.String target, @org.jetbrains.annotations.Nullable()
    java.lang.String exchange) {
        super();
    }
}