//package ru.justd.cryptobot
//
//import com.nhaarman.mockito_kotlin.mock
//import com.nhaarman.mockito_kotlin.verify
//import com.nhaarman.mockito_kotlin.whenever
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Before
//import org.junit.Test
//import ru.justd.cryptobot.di.StorageModule
//import ru.justd.cryptobot.exchanges.ExchangeFacade
//import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
//import ru.justd.cryptobot.exchanges.gdax.GdaxApi
//import ru.justd.cryptobot.handler.CommandHandlerFacade
//import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
//import ru.justd.cryptobot.handler.exceptions.InvalidCommand
//import ru.justd.cryptobot.handler.subscribe.Subscription
//
//internal class SubscribeTest {
//
////    lateinit var testInstance: TelegramCryptAdviser
//    lateinit var commandHandlerFacade : CommandHandlerFacade
//    private val storageMock = StorageModule.storageMock
//    private val userId = "chatId"
//
//    private val BASE_LTC = "LTC"
//    private val BASE_BCC = "BCC"
//    private val TARGET_GBP = "GBP"
//    private val TARGET_EUR = "EUR"
//    private val EXCHANGE_GDAX = GdaxApi.NAME
//    private val EXCHANGE_CRYPTONATOR = CryptonatorApi.NAME
//
//    @Before
//    fun setup() {
//        commandHandlerFacade = CommandHandlerFacadeImpl(mutableListOf())
////        testInstance = TelegramCryptAdviser()
////        testInstance.run()
//
//        whenever(storageMock.getExchangeApi(userId)).thenReturn("stub api")
//        whenever(storageMock.getBaseCurrency(userId)).thenReturn("stub base")
//        whenever(storageMock.getTargetCurrency(userId)).thenReturn("stub target")
//    }
//
//    @Test
//    fun `test base is absent throws exception`() {
//        try {
//            //action
//            commandHandlerFacade.handle(userId, "/subscribe")
//        } catch (e: Exception) {
//
//            //test
//            assertThat(e).isExactlyInstanceOf(InvalidCommand::class.java)
//            assertThat(e.message).isEqualTo("Invalid format, please try `/subscribe BASE TARGER {EXCHANGE}`")
//        }
//    }
//
//    @Test
//    fun `test subscribe with ltc gbp`() {
//        //action
//        val response = commandHandlerFacade.createCommandHandler(userId, "/subscribe $TARGET_GBP $TARGET_GBP").responseMessage()
//
//        //test
//        assertThat(response.text).isEqualTo("subscriptions created")
//    }
//
////    @Test
////    fun `test subscribe btc usd gdax`() { //todo not working because of asynchronousy
////        //action
////        val response = commandHandlerFacade.createCommandHandler(userId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_GDAX").responseMessage()
////
////        //test
////        assertThat(response.text).isEqualTo("subscriptions created")
////        verify(storageMock).addSubscription(userId, Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5))
////    }
//
//    @Test
//    fun `test multiple subscriptions`() {
//        //action
//        commandHandlerFacade.createCommandHandler(userId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_GDAX").responseMessage()
//        commandHandlerFacade.createCommandHandler(userId, "/subscribe $BASE_BCC $TARGET_EUR $EXCHANGE_CRYPTONATOR").responseMessage()
//
//        //test
//        verify(storageMock).addSubscription(userId, Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5))
//        verify(storageMock).addSubscription(userId, Subscription(BASE_BCC, TARGET_EUR, EXCHANGE_CRYPTONATOR, 5))
//    }
//}