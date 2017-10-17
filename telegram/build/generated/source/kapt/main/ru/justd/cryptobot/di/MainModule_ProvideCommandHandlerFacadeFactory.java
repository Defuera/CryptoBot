package ru.justd.cryptobot.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import ru.justd.cryptobot.UserPreferences;
import ru.justd.cryptobot.exchanges.ExchangeFacade;
import ru.justd.cryptobot.handler.CommandHandlerFacade;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainModule_ProvideCommandHandlerFacadeFactory
    implements Factory<CommandHandlerFacade> {
  private final MainModule module;

  private final Provider<ExchangeFacade> exchangeFacadeProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public MainModule_ProvideCommandHandlerFacadeFactory(
      MainModule module,
      Provider<ExchangeFacade> exchangeFacadeProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    assert module != null;
    this.module = module;
    assert exchangeFacadeProvider != null;
    this.exchangeFacadeProvider = exchangeFacadeProvider;
    assert userPreferencesProvider != null;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public CommandHandlerFacade get() {
    return Preconditions.checkNotNull(
        module.provideCommandHandlerFacade(
            exchangeFacadeProvider.get(), userPreferencesProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<CommandHandlerFacade> create(
      MainModule module,
      Provider<ExchangeFacade> exchangeFacadeProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new MainModule_ProvideCommandHandlerFacadeFactory(
        module, exchangeFacadeProvider, userPreferencesProvider);
  }
}
