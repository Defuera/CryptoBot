package ru.justd.cryptobot.messenger;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\fH\u0002J!\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0010H\u0002\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016JR\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182:\u0010\u0019\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0006\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u001e\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00140\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lru/justd/cryptobot/messenger/MessageSender;", "", "telegramBot", "Lcom/pengrad/telegrambot/TelegramBot;", "(Lcom/pengrad/telegrambot/TelegramBot;)V", "createSendMessageRequest", "Lcom/pengrad/telegrambot/request/SendMessage;", "chatId", "", "message", "Lru/justd/cryptobot/messenger/model/Message;", "formatMessageText", "", "mapToTelegramKeyboard", "Lcom/pengrad/telegrambot/model/request/Keyboard;", "keyboard", "", "Lru/justd/cryptobot/messenger/model/AnswerCase;", "([[Lru/justd/cryptobot/messenger/model/AnswerCase;)Lcom/pengrad/telegrambot/model/request/Keyboard;", "sendMessage", "", "commandHandler", "Lru/justd/cryptobot/handler/CommandHandler;", "outgoingMessage", "Lru/justd/cryptobot/messaging/model/OutgoingMessage;", "onSuccess", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "request", "Lcom/pengrad/telegrambot/response/SendResponse;", "response", "telegram"})
public final class MessageSender {
    private final com.pengrad.telegrambot.TelegramBot telegramBot = null;
    
    public final void sendMessage(long chatId, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.handler.CommandHandler commandHandler) {
    }
    
    public final void sendMessage(long chatId, @org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.messaging.model.OutgoingMessage outgoingMessage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.pengrad.telegrambot.request.SendMessage, ? super com.pengrad.telegrambot.response.SendResponse, kotlin.Unit> onSuccess) {
    }
    
    private final com.pengrad.telegrambot.request.SendMessage createSendMessageRequest(long chatId, ru.justd.cryptobot.messenger.model.Message message) {
        return null;
    }
    
    private final com.pengrad.telegrambot.model.request.Keyboard mapToTelegramKeyboard(ru.justd.cryptobot.messenger.model.AnswerCase[][] keyboard) {
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