package ru.justd.cryptobot.messaging.model;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0007J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lru/justd/cryptobot/messaging/model/ResponseCase;", "T", "", "appearance", "Lru/justd/cryptobot/messaging/model/ResponseCase$Appearance;", "title", "", "Appearance", "core"})
public abstract interface ResponseCase<T extends java.lang.Object> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract ru.justd.cryptobot.messaging.model.ResponseCase.Appearance appearance();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String title();
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lru/justd/cryptobot/messaging/model/ResponseCase$Appearance;", "", "(Ljava/lang/String;I)V", "FULL", "HALF", "THIRD", "QUARTER", "core"})
    public static enum Appearance {
        /*public static final*/ FULL /* = new FULL() */,
        /*public static final*/ HALF /* = new HALF() */,
        /*public static final*/ THIRD /* = new THIRD() */,
        /*public static final*/ QUARTER /* = new QUARTER() */;
        
        Appearance() {
        }
    }
}