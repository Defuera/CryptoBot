package ru.justd.cryptobot.handler;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lru/justd/cryptobot/handler/CommandHandlerFacadeImpl;", "Lru/justd/cryptobot/handler/CommandHandlerFacade;", "exchange", "Lru/justd/cryptobot/exchanges/ExchangeFacade;", "userPreferences", "Lru/justd/cryptobot/UserPreferences;", "(Lru/justd/cryptobot/exchanges/ExchangeFacade;Lru/justd/cryptobot/UserPreferences;)V", "createCommandHandler", "Lru/justd/cryptobot/handler/CommandHandler;", "requestMessage", "", "find", "Lru/justd/cryptobot/handler/Command;", "incomingMessage", "findCommandHandlerFactory", "Lru/justd/cryptobot/handler/CommandHandlerFactory;", "matches", "", "command", "message", "core"})
public final class CommandHandlerFacadeImpl implements ru.justd.cryptobot.handler.CommandHandlerFacade {
    private final ru.justd.cryptobot.exchanges.ExchangeFacade exchange = null;
    private final ru.justd.cryptobot.UserPreferences userPreferences = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.handler.CommandHandler createCommandHandler(@org.jetbrains.annotations.NotNull()
    java.lang.String requestMessage) {
        return null;
    }
    
    private final ru.justd.cryptobot.handler.CommandHandlerFactory<ru.justd.cryptobot.handler.CommandHandler> findCommandHandlerFactory(java.lang.String incomingMessage) {
        return null;
    }
    
    private final ru.justd.cryptobot.handler.Command find(java.lang.String incomingMessage) {
        return null;
    }
    
    private final boolean matches(ru.justd.cryptobot.handler.Command command, java.lang.String message) {
        return false;
    }
    
    public CommandHandlerFacadeImpl(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.exchanges.ExchangeFacade exchange, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.UserPreferences userPreferences) {
        super();
    }
}