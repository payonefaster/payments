package pay.one.faster.payments.infra;


import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.ext.web.client.WebClient;
import pay.one.faster.payments.domain.authorization.TransactionRequest;

public class AuthorizationRequestVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationRequestVerticle.class);

  private final String AUTHORIZATION_SVC = System.getenv("AUTHORIZATION_SVC");

  @Override
  public void start() throws Exception {
    final EventBus eventBus = this.vertx.eventBus();
    eventBus.consumer("authorization.request").toObservable().subscribe(message ->{
      final TransactionRequest transactionRequest = Json
          .decodeValue(message.body().toString(), TransactionRequest.class);
      LOGGER.info("Creating transaction for card number: " + transactionRequest.getCardNumber());

      final WebClient webClient = WebClient.create(this.vertx);
      webClient.postAbs(AUTHORIZATION_SVC).rxSendJson(message.body());
    });
  }

}
