package ru.justd.cryptobot.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import ru.justd.cryptobot.handler.CommandHandlerFacade;
import ru.justd.cryptobot.messenger.MessageReceiver;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MainModule_ProvideMessageReceiverFactory implements Factory<MessageReceiver> {
  private final MainModule module;

  private final Provider<CommandHandlerFacade> commandHandlerFacadeProvider;

  public MainModule_ProvideMessageReceiverFactory(
      MainModule module, Provider<CommandHandlerFacade> commandHandlerFacadeProvider) {
    assert module != null;
    this.module = module;
    assert commandHandlerFacadeProvider != null;
    this.commandHandlerFacadeProvider = commandHandlerFacadeProvider;
  }

  @Override
  public MessageReceiver get() {
    return Preconditions.checkNotNull(
        module.provideMessageReceiver(commandHandlerFacadeProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MessageReceiver> create(
      MainModule module, Provider<CommandHandlerFacade> commandHandlerFacadeProvider) {
    return new MainModule_ProvideMessageReceiverFactory(module, commandHandlerFacadeProvider);
  }
}
