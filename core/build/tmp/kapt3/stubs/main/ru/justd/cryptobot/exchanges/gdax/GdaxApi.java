package ru.justd.cryptobot.exchanges.gdax;

@kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lru/justd/cryptobot/exchanges/gdax/GdaxApi;", "Lru/justd/cryptobot/exchanges/PollingExchange;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "BASE_URL", "", "getRateUrl", "base", "target", "parseResponseBody", "Lru/justd/cryptobot/exchanges/RateResponse;", "bodyString", "Companion", "Envelope", "core"})
public final class GdaxApi extends ru.justd.cryptobot.exchanges.PollingExchange {
    private final java.lang.String BASE_URL = "https://api.gdax.com";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME = "GDAX";
    public static final ru.justd.cryptobot.exchanges.gdax.GdaxApi.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getRateUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ru.justd.cryptobot.exchanges.RateResponse parseResponseBody(@org.jetbrains.annotations.NotNull()
    java.lang.String bodyString, @org.jetbrains.annotations.NotNull()
    java.lang.String base, @org.jetbrains.annotations.NotNull()
    java.lang.String target) throws ru.justd.cryptobot.exchanges.exceptions.RequestFailed {
        return null;
    }
    
    public GdaxApi(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0015\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\u0016\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJV\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R!\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR!\u0010\u0006\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001f"}, d2 = {"Lru/justd/cryptobot/exchanges/gdax/GdaxApi$Envelope;", "", "errorMessage", "", "sequence", "", "bids", "", "asks", "(Ljava/lang/String;Ljava/lang/Long;[[Ljava/lang/String;[[Ljava/lang/String;)V", "getAsks", "()[[Ljava/lang/String;", "[[Ljava/lang/String;", "getBids", "getErrorMessage", "()Ljava/lang/String;", "getSequence", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Long;[[Ljava/lang/String;[[Ljava/lang/String;)Lru/justd/cryptobot/exchanges/gdax/GdaxApi$Envelope;", "equals", "", "other", "hashCode", "", "toString", "core"})
    static final class Envelope {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "message")
        private final java.lang.String errorMessage = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long sequence = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String[][] bids = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String[][] asks = null;
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getErrorMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getSequence() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String[][] getBids() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String[][] getAsks() {
            return null;
        }
        
        public Envelope(@org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
        java.lang.Long sequence, @org.jetbrains.annotations.Nullable()
        java.lang.String[][] bids, @org.jetbrains.annotations.Nullable()
        java.lang.String[][] asks) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String[][] component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String[][] component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ru.justd.cryptobot.exchanges.gdax.GdaxApi.Envelope copy(@org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
        java.lang.Long sequence, @org.jetbrains.annotations.Nullable()
        java.lang.String[][] bids, @org.jetbrains.annotations.Nullable()
        java.lang.String[][] asks) {
            return null;
        }
        
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(java.lang.Object p0) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 7}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lru/justd/cryptobot/exchanges/gdax/GdaxApi$Companion;", "", "()V", "NAME", "", "core"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}