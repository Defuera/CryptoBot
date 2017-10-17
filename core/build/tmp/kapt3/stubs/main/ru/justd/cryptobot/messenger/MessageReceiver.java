package ru.justd.cryptobot.messenger;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R.\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u001b"}, d2 = {"Lru/justd/cryptobot/messenger/MessageReceiver;", "", "commandHandlerFacade", "Lru/justd/cryptobot/handler/CommandHandlerFacade;", "(Lru/justd/cryptobot/handler/CommandHandlerFacade;)V", "onProcessListener", "Lkotlin/Function2;", "", "Lru/justd/cryptobot/handler/CommandHandler;", "", "getOnProcessListener", "()Lkotlin/jvm/functions/Function2;", "setOnProcessListener", "(Lkotlin/jvm/functions/Function2;)V", "greetUserIfNeeded", "message", "Lcom/pengrad/telegrambot/model/Message;", "handleBotCommand", "handleCallbackQuery", "callbackQuery", "Lcom/pengrad/telegrambot/model/CallbackQuery;", "handleIncomingMessage", "isBotAddedToChannel", "", "processUpdate", "update", "Lcom/pengrad/telegrambot/model/Update;", "core"})
public final class MessageReceiver {
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function2<? super java.lang.Long, ? super ru.justd.cryptobot.handler.CommandHandler, kotlin.Unit> onProcessListener;
    private final ru.justd.cryptobot.handler.CommandHandlerFacade commandHandlerFacade = null;
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function2<java.lang.Long, ru.justd.cryptobot.handler.CommandHandler, kotlin.Unit> getOnProcessListener() {
        return null;
    }
    
    public final void setOnProcessListener(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super java.lang.Long, ? super ru.justd.cryptobot.handler.CommandHandler, kotlin.Unit> p0) {
    }
    
    public final void processUpdate(@org.jetbrains.annotations.NotNull()
    com.pengrad.telegrambot.model.Update update) {
    }
    
    private final void handleIncomingMessage(com.pengrad.telegrambot.model.Message message) {
    }
    
    private final void greetUserIfNeeded(com.pengrad.telegrambot.model.Message message) {
    }
    
    private final void handleCallbackQuery(com.pengrad.telegrambot.model.CallbackQuery callbackQuery) {
    }
    
    private final void handleBotCommand(com.pengrad.telegrambot.model.Message message) {
    }
    
    private final boolean isBotAddedToChannel(com.pengrad.telegrambot.model.Message message) {
        return false;
    }
    
    public MessageReceiver(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.handler.CommandHandlerFacade commandHandlerFacade) {
        super();
    }
}