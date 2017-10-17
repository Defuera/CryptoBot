package ru.justd.cryptobot.messenger;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0002J\u0014\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014JR\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\n2:\u0010\u0016\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0006\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\u001b\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00120\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lru/justd/cryptobot/messenger/MessageSender;", "", "telegramBot", "Lcom/pengrad/telegrambot/TelegramBot;", "(Lcom/pengrad/telegrambot/TelegramBot;)V", "createSendMessageRequest", "Lcom/pengrad/telegrambot/request/SendMessage;", "chatId", "", "message", "Lru/justd/cryptobot/messenger/model/OutgoingMessage;", "formatMessageText", "", "mapToTelegramKeyboard", "Lcom/pengrad/telegrambot/model/request/Keyboard;", "keyboard", "Lru/justd/cryptobot/messenger/model/Responses;", "sendMessage", "", "commandHandler", "Lru/justd/cryptobot/handler/CommandHandler;", "outgoingMessage", "onSuccess", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "request", "Lcom/pengrad/telegrambot/response/SendResponse;", "response", "core"})
public final class MessageSender {
    private final com.pengrad.telegrambot.TelegramBot telegramBot = null;
    
    public final void sendMessage(long chatId, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.handler.CommandHandler commandHandler) {
    }
    
    public final void sendMessage(long chatId, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.messenger.model.OutgoingMessage outgoingMessage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.pengrad.telegrambot.request.SendMessage, ? super com.pengrad.telegrambot.response.SendResponse, kotlin.Unit> onSuccess) {
    }
    
    private final com.pengrad.telegrambot.request.SendMessage createSendMessageRequest(long chatId, ru.justd.cryptobot.messenger.model.OutgoingMessage message) {
        return null;
    }
    
    private final com.pengrad.telegrambot.model.request.Keyboard mapToTelegramKeyboard(ru.justd.cryptobot.messenger.model.Responses<?> keyboard) {
        return null;
    }
    
    private final java.lang.String formatMessageText(java.lang.String message) {
        return null;
    }
    
    public MessageSender(@org.jetbrains.annotations.NotNull()
    com.pengrad.telegrambot.TelegramBot telegramBot) {
        super();
    }
}