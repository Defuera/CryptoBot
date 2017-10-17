package ru.justd.cryptobot.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import ru.justd.cryptobot.UserPreferences;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class StorageModule_ProvideUserPreferencesFactory implements Factory<UserPreferences> {
  private final StorageModule module;

  public StorageModule_ProvideUserPreferencesFactory(StorageModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public UserPreferences get() {
    return Preconditions.checkNotNull(
        module.provideUserPreferences(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<UserPreferences> create(StorageModule module) {
    return new StorageModule_ProvideUserPreferencesFactory(module);
  }
}
