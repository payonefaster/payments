package pay.one.faster.payments.infra;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.client.predicate.ResponsePredicate;
import pay.one.faster.payments.domain.PaymentFlow;
import pay.one.faster.payments.domain.PaymentStatus;
import pay.one.faster.payments.domain.requester.CheckRequestEligibility;

public class ValidateRequesterVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(ValidateRequesterVerticle.class);

  private final String REQUESTER_SVC = System.getenv("REQUESTER_SVC");

  @Override
  public void start() throws Exception {
    final EventBus eventBus = this.vertx.eventBus();
    eventBus.consumer("requester.check.able").toObservable().subscribe(message ->{
      final CheckRequestEligibility checkRequestEligibility = Json.decodeValue(message.body().toString(), CheckRequestEligibility.class);
      LOGGER.info("Check Requester Eligibility for RequesterId: " + checkRequestEligibility.getRequesterId());
      final WebClient webClient = WebClient.create(this.vertx);
      webClient.getAbs(REQUESTER_SVC).rxSend().subscribe(data ->{
        final String paymentId = message.headers().get("payment-id");
        if(data.statusCode() != 200){
          message.rxReply(new JsonObject(Json.encode(new PaymentFlow(paymentId, PaymentStatus.BLOCKED_REQUESTER))));
        }else{
          message.rxReply(new JsonObject(Json.encode(new PaymentFlow(paymentId, PaymentStatus.VALID_REQUESTER))));
        }
      });
    });
  }

}
