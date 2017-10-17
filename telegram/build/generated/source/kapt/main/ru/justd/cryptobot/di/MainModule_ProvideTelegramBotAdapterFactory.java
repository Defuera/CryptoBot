package ru.justd.cryptobot.di;

import com.pengrad.telegrambot.TelegramBot;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainModule_ProvideTelegramBotAdapterFactory implements Factory<TelegramBot> {
  private final MainModule module;

  public MainModule_ProvideTelegramBotAdapterFactory(MainModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public TelegramBot get() {
    return Preconditions.checkNotNull(
        module.provideTelegramBotAdapter(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<TelegramBot> create(MainModule module) {
    return new MainModule_ProvideTelegramBotAdapterFactory(module);
  }
}
