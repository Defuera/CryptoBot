package ru.justd.cryptobot.handler.kill;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lru/justd/cryptobot/handler/kill/KillCommandHandler;", "Lru/justd/cryptobot/handler/CommandHandler;", "kill", "", "(Z)V", "responseMessage", "Lru/justd/cryptobot/messenger/model/OutgoingMessage;", "Companion", "core"})
public final class KillCommandHandler implements ru.justd.cryptobot.handler.CommandHandler {
    private final boolean kill = false;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FAREWELL_MESSAGE = "I was glad to serve you! Farewell!";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SURVIVOR_MESSAGE = "Phew! It\'s not me!";
    public static final ru.justd.cryptobot.handler.kill.KillCommandHandler.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.messenger.model.OutgoingMessage responseMessage() {
        return null;
    }
    
    public KillCommandHandler(boolean kill) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lru/justd/cryptobot/handler/kill/KillCommandHandler$Companion;", "", "()V", "FAREWELL_MESSAGE", "", "SURVIVOR_MESSAGE", "core"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}