package ru.justd.cryptobot.handler;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003J\r\u0010\u0004\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lru/justd/cryptobot/handler/CommandHandlerFactory;", "T", "Lru/justd/cryptobot/handler/CommandHandler;", "", "create", "()Lru/justd/cryptobot/handler/CommandHandler;", "core"})
public abstract interface CommandHandlerFactory<T extends ru.justd.cryptobot.handler.CommandHandler> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract T create();
}