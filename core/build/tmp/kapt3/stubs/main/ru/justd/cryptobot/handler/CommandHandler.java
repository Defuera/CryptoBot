package ru.justd.cryptobot.handler;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lru/justd/cryptobot/handler/CommandHandler;", "", "responseMessage", "Lru/justd/cryptobot/messenger/model/OutgoingMessage;", "core"})
public abstract interface CommandHandler {
    
    @org.jetbrains.annotations.NotNull()
    public abstract ru.justd.cryptobot.messenger.model.OutgoingMessage responseMessage();
}