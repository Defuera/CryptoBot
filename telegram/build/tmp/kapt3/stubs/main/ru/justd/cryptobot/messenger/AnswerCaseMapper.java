package ru.justd.cryptobot.messenger;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lru/justd/cryptobot/messenger/AnswerCaseMapper;", "R", "Lru/justd/cryptobot/messaging/model/ResponseCase;", "", "map", "Lru/justd/cryptobot/messenger/model/AnswerCase;", "response", "(Lru/justd/cryptobot/messaging/model/ResponseCase;)Lru/justd/cryptobot/messenger/model/AnswerCase;", "telegram"})
public abstract interface AnswerCaseMapper<R extends ru.justd.cryptobot.messaging.model.ResponseCase> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract ru.justd.cryptobot.messenger.model.AnswerCase map(@org.jetbrains.annotations.NotNull()
    R response);
}