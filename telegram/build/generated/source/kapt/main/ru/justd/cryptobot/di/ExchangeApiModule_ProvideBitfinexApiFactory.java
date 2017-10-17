package ru.justd.cryptobot.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import ru.justd.cryptobot.exchanges.ExchangeApi;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ExchangeApiModule_ProvideBitfinexApiFactory implements Factory<ExchangeApi> {
  private final ExchangeApiModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public ExchangeApiModule_ProvideBitfinexApiFactory(
      ExchangeApiModule module, Provider<OkHttpClient> okHttpClientProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public ExchangeApi get() {
    return Preconditions.checkNotNull(
        module.provideBitfinexApi(okHttpClientProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ExchangeApi> create(
      ExchangeApiModule module, Provider<OkHttpClient> okHttpClientProvider) {
    return new ExchangeApiModule_ProvideBitfinexApiFactory(module, okHttpClientProvider);
  }
}
