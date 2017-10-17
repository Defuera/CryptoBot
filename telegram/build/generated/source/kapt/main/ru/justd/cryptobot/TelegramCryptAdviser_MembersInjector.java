package ru.justd.cryptobot;

import com.pengrad.telegrambot.TelegramBot;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;
import ru.justd.cryptobot.messenger.MessageReceiver;
import ru.justd.cryptobot.messenger.MessageSender;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TelegramCryptAdviser_MembersInjector
    implements MembersInjector<TelegramCryptAdviser> {
  private final Provider<TelegramBot> telegramBotProvider;

  private final Provider<MessageReceiver> messageReceiverProvider;

  private final Provider<MessageSender> messageSenderProvider;

  public TelegramCryptAdviser_MembersInjector(
      Provider<TelegramBot> telegramBotProvider,
      Provider<MessageReceiver> messageReceiverProvider,
      Provider<MessageSender> messageSenderProvider) {
    assert telegramBotProvider != null;
    this.telegramBotProvider = telegramBotProvider;
    assert messageReceiverProvider != null;
    this.messageReceiverProvider = messageReceiverProvider;
    assert messageSenderProvider != null;
    this.messageSenderProvider = messageSenderProvider;
  }

  public static MembersInjector<TelegramCryptAdviser> create(
      Provider<TelegramBot> telegramBotProvider,
      Provider<MessageReceiver> messageReceiverProvider,
      Provider<MessageSender> messageSenderProvider) {
    return new TelegramCryptAdviser_MembersInjector(
        telegramBotProvider, messageReceiverProvider, messageSenderProvider);
  }

  @Override
  public void injectMembers(TelegramCryptAdviser instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.telegramBot = telegramBotProvider.get();
    instance.messageReceiver = messageReceiverProvider.get();
    instance.messageSender = messageSenderProvider.get();
  }
}
