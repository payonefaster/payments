package pay.one.faster.payments.infra;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.ext.mongo.MongoClient;
import pay.one.faster.payments.domain.Payment;
import pay.one.faster.payments.domain.PaymentId;
import pay.one.faster.payments.domain.PaymentRequest;

/** @author claudioed on 2019-02-23. Project payments */
public class RegisterPaymentVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(RegisterPaymentVerticle.class);

  private final String databaseName = System.getenv("DB_NAME");

  private final String databaseUrl = System.getenv("DB_URL");

  @Override
  public void start() {
    final JsonObject mongoConfig =
        new JsonObject()
            .put("connection_string", this.databaseUrl)
            .put("db_name", this.databaseName);

    final MongoClient client = MongoClient.createShared(this.vertx, mongoConfig);

    final EventBus eventBus = this.vertx.eventBus();
    eventBus
        .consumer("payment.register")
        .toObservable()
        .subscribe(
            message -> {
              LOGGER.info("Receiving payment register " + message.body().toString());
              final PaymentRequest paymentRequest =
                  Json.decodeValue(message.body().toString(), PaymentRequest.class);

              final PaymentId paymentId =
                  new PaymentId(
                      paymentRequest.getCard().getNumber(),
                      paymentRequest.getOrder().getValue(),
                      paymentRequest.getAt(),
                      paymentRequest.getType());

              final Payment payment =
                  new Payment(
                      paymentId.id(),
                      paymentRequest.getOrder(),
                      paymentRequest.getAddress(),
                      paymentRequest.getCard(),
                      paymentRequest.getCustomer(),
                      paymentRequest.getType());

              client
                  .rxInsert("payments", new JsonObject(Json.encode(payment)))
                  .subscribe(
                      id -> {
                        LOGGER.info("new payment document id: " + id);
                      });
            });
  }
}
