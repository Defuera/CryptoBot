package ru.justd.cryptobot.di;

import com.pengrad.telegrambot.TelegramBot;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import ru.justd.cryptobot.messenger.MessageSender;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainModule_ProvideMessageSenderFactory implements Factory<MessageSender> {
  private final MainModule module;

  private final Provider<TelegramBot> telegramBotProvider;

  public MainModule_ProvideMessageSenderFactory(
      MainModule module, Provider<TelegramBot> telegramBotProvider) {
    assert module != null;
    this.module = module;
    assert telegramBotProvider != null;
    this.telegramBotProvider = telegramBotProvider;
  }

  @Override
  public MessageSender get() {
    return Preconditions.checkNotNull(
        module.provideMessageSender(telegramBotProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MessageSender> create(
      MainModule module, Provider<TelegramBot> telegramBotProvider) {
    return new MainModule_ProvideMessageSenderFactory(module, telegramBotProvider);
  }
}
