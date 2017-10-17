package ru.justd.cryptobot.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import ru.justd.cryptobot.UserPreferences;
import ru.justd.cryptobot.exchanges.ExchangeApi;
import ru.justd.cryptobot.exchanges.ExchangeFacade;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ExchangeApiModule_ProvideExchangeFacadeFactory
    implements Factory<ExchangeFacade> {
  private final ExchangeApiModule module;

  private final Provider<ExchangeApi> gdaxApiProvider;

  private final Provider<ExchangeApi> coinbaseApiProvider;

  private final Provider<ExchangeApi> cryptonatorApiProvider;

  private final Provider<ExchangeApi> bitfinexApiProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public ExchangeApiModule_ProvideExchangeFacadeFactory(
      ExchangeApiModule module,
      Provider<ExchangeApi> gdaxApiProvider,
      Provider<ExchangeApi> coinbaseApiProvider,
      Provider<ExchangeApi> cryptonatorApiProvider,
      Provider<ExchangeApi> bitfinexApiProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    assert module != null;
    this.module = module;
    assert gdaxApiProvider != null;
    this.gdaxApiProvider = gdaxApiProvider;
    assert coinbaseApiProvider != null;
    this.coinbaseApiProvider = coinbaseApiProvider;
    assert cryptonatorApiProvider != null;
    this.cryptonatorApiProvider = cryptonatorApiProvider;
    assert bitfinexApiProvider != null;
    this.bitfinexApiProvider = bitfinexApiProvider;
    assert userPreferencesProvider != null;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public ExchangeFacade get() {
    return Preconditions.checkNotNull(
        module.provideExchangeFacade(
            gdaxApiProvider.get(),
            coinbaseApiProvider.get(),
            cryptonatorApiProvider.get(),
            bitfinexApiProvider.get(),
            userPreferencesProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ExchangeFacade> create(
      ExchangeApiModule module,
      Provider<ExchangeApi> gdaxApiProvider,
      Provider<ExchangeApi> coinbaseApiProvider,
      Provider<ExchangeApi> cryptonatorApiProvider,
      Provider<ExchangeApi> bitfinexApiProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new ExchangeApiModule_ProvideExchangeFacadeFactory(
        module,
        gdaxApiProvider,
        coinbaseApiProvider,
        cryptonatorApiProvider,
        bitfinexApiProvider,
        userPreferencesProvider);
  }
}
