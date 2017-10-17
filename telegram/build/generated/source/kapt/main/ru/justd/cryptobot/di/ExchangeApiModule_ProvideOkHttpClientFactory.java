package ru.justd.cryptobot.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ExchangeApiModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final ExchangeApiModule module;

  public ExchangeApiModule_ProvideOkHttpClientFactory(ExchangeApiModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.provideOkHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(ExchangeApiModule module) {
    return new ExchangeApiModule_ProvideOkHttpClientFactory(module);
  }
}
