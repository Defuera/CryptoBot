package ru.justd.cryptobot;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006 "}, d2 = {"Lru/justd/cryptobot/TelegramCryptAdviser;", "", "()V", "messageReceiver", "Lru/justd/cryptobot/messenger/MessageReceiver;", "getMessageReceiver", "()Lru/justd/cryptobot/messenger/MessageReceiver;", "setMessageReceiver", "(Lru/justd/cryptobot/messenger/MessageReceiver;)V", "messageSender", "Lru/justd/cryptobot/messenger/MessageSender;", "getMessageSender", "()Lru/justd/cryptobot/messenger/MessageSender;", "setMessageSender", "(Lru/justd/cryptobot/messenger/MessageSender;)V", "telegramBot", "Lcom/pengrad/telegrambot/TelegramBot;", "getTelegramBot", "()Lcom/pengrad/telegrambot/TelegramBot;", "setTelegramBot", "(Lcom/pengrad/telegrambot/TelegramBot;)V", "initMessageReceiver", "", "initMessageSender", "inject", "killInstance", "chatId", "", "processUpdate", "update", "Lcom/pengrad/telegrambot/model/Update;", "run", "telegram"})
public final class TelegramCryptAdviser {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.pengrad.telegrambot.TelegramBot telegramBot;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public ru.justd.cryptobot.messenger.MessageReceiver messageReceiver;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public ru.justd.cryptobot.messenger.MessageSender messageSender;
    
    @org.jetbrains.annotations.NotNull()
    public final com.pengrad.telegrambot.TelegramBot getTelegramBot() {
        return null;
    }
    
    public final void setTelegramBot(@org.jetbrains.annotations.NotNull()
    com.pengrad.telegrambot.TelegramBot p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final ru.justd.cryptobot.messenger.MessageReceiver getMessageReceiver() {
        return null;
    }
    
    public final void setMessageReceiver(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.messenger.MessageReceiver p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final ru.justd.cryptobot.messenger.MessageSender getMessageSender() {
        return null;
    }
    
    public final void setMessageSender(@org.jetbrains.annotations.NotNull()
    ru.justd.cryptobot.messenger.MessageSender p0) {
    }
    
    public final void run() {
    }
    
    private final void inject() {
    }
    
    private final void initMessageSender() {
    }
    
    private final void initMessageReceiver() {
    }
    
    private final void processUpdate(com.pengrad.telegrambot.model.Update update) {
    }
    
    private final void killInstance(long chatId) {
    }
    
    public TelegramCryptAdviser() {
        super();
    }
}