package ru.justd.cryptobot;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lru/justd/cryptobot/CryptAdviser;", "", "handleCommand", "", "requestMessage", "publishUpdate", "", "core"})
public abstract interface CryptAdviser {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String handleCommand(@org.jetbrains.annotations.NotNull()
    java.lang.String requestMessage);
    
    public abstract void publishUpdate();
}