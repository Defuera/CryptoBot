package ru.justd.cryptobot.di;

import com.pengrad.telegrambot.TelegramBot;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import ru.justd.cryptobot.TelegramCryptAdviser;
import ru.justd.cryptobot.TelegramCryptAdviser_MembersInjector;
import ru.justd.cryptobot.UserPreferences;
import ru.justd.cryptobot.exchanges.ExchangeApi;
import ru.justd.cryptobot.exchanges.ExchangeFacade;
import ru.justd.cryptobot.handler.CommandHandlerFacade;
import ru.justd.cryptobot.messenger.MessageReceiver;
import ru.justd.cryptobot.messenger.MessageSender;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerMainComponent implements MainComponent {
  private Provider<TelegramBot> provideTelegramBotAdapterProvider;

  private Provider<OkHttpClient> provideOkHttpClientProvider;

  private Provider<ExchangeApi> provideGdaxApiProvider;

  private Provider<ExchangeApi> provideCoinbaseApiProvider;

  private Provider<ExchangeApi> provideCryptonatorProvider;

  private Provider<ExchangeApi> provideBitfinexApiProvider;

  private Provider<UserPreferences> provideUserPreferencesProvider;

  private Provider<ExchangeFacade> provideExchangeFacadeProvider;

  private Provider<CommandHandlerFacade> provideCommandHandlerFacadeProvider;

  private Provider<MessageReceiver> provideMessageReceiverProvider;

  private Provider<MessageSender> provideMessageSenderProvider;

  private MembersInjector<TelegramCryptAdviser> telegramCryptAdviserMembersInjector;

  private DaggerMainComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static MainComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideTelegramBotAdapterProvider =
        DoubleCheck.provider(
            MainModule_ProvideTelegramBotAdapterFactory.create(builder.mainModule));

    this.provideOkHttpClientProvider =
        DoubleCheck.provider(
            ExchangeApiModule_ProvideOkHttpClientFactory.create(builder.exchangeApiModule));

    this.provideGdaxApiProvider =
        DoubleCheck.provider(
            ExchangeApiModule_ProvideGdaxApiFactory.create(
                builder.exchangeApiModule, provideOkHttpClientProvider));

    this.provideCoinbaseApiProvider =
        DoubleCheck.provider(
            ExchangeApiModule_ProvideCoinbaseApiFactory.create(
                builder.exchangeApiModule, provideOkHttpClientProvider));

    this.provideCryptonatorProvider =
        DoubleCheck.provider(
            ExchangeApiModule_ProvideCryptonatorFactory.create(
                builder.exchangeApiModule, provideOkHttpClientProvider));

    this.provideBitfinexApiProvider =
        DoubleCheck.provider(
            ExchangeApiModule_ProvideBitfinexApiFactory.create(
                builder.exchangeApiModule, provideOkHttpClientProvider));

    this.provideUserPreferencesProvider =
        DoubleCheck.provider(
            StorageModule_ProvideUserPreferencesFactory.create(builder.storageModule));

    this.provideExchangeFacadeProvider =
        DoubleCheck.provider(
            ExchangeApiModule_ProvideExchangeFacadeFactory.create(
                builder.exchangeApiModule,
                provideGdaxApiProvider,
                provideCoinbaseApiProvider,
                provideCryptonatorProvider,
                provideBitfinexApiProvider,
                provideUserPreferencesProvider));

    this.provideCommandHandlerFacadeProvider =
        DoubleCheck.provider(
            MainModule_ProvideCommandHandlerFacadeFactory.create(
                builder.mainModule, provideExchangeFacadeProvider, provideUserPreferencesProvider));

    this.provideMessageReceiverProvider =
        DoubleCheck.provider(
            MainModule_ProvideMessageReceiverFactory.create(
                builder.mainModule, provideCommandHandlerFacadeProvider));

    this.provideMessageSenderProvider =
        DoubleCheck.provider(
            MainModule_ProvideMessageSenderFactory.create(
                builder.mainModule, provideTelegramBotAdapterProvider));

    this.telegramCryptAdviserMembersInjector =
        TelegramCryptAdviser_MembersInjector.create(
            provideTelegramBotAdapterProvider,
            provideMessageReceiverProvider,
            provideMessageSenderProvider);
  }

  @Override
  public void inject(TelegramCryptAdviser main) {
    telegramCryptAdviserMembersInjector.injectMembers(main);
  }

  public static final class Builder {
    private MainModule mainModule;

    private ExchangeApiModule exchangeApiModule;

    private StorageModule storageModule;

    private Builder() {}

    public MainComponent build() {
      if (mainModule == null) {
        this.mainModule = new MainModule();
      }
      if (exchangeApiModule == null) {
        this.exchangeApiModule = new ExchangeApiModule();
      }
      if (storageModule == null) {
        this.storageModule = new StorageModule();
      }
      return new DaggerMainComponent(this);
    }

    public Builder mainModule(MainModule mainModule) {
      this.mainModule = Preconditions.checkNotNull(mainModule);
      return this;
    }

    public Builder exchangeApiModule(ExchangeApiModule exchangeApiModule) {
      this.exchangeApiModule = Preconditions.checkNotNull(exchangeApiModule);
      return this;
    }

    public Builder storageModule(StorageModule storageModule) {
      this.storageModule = Preconditions.checkNotNull(storageModule);
      return this;
    }
  }
}
