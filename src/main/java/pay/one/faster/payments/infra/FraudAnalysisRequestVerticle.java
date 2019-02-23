package pay.one.faster.payments.infra;

import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.ext.web.client.WebClient;
import pay.one.faster.payments.domain.fraud.FraudAnalysisRequest;

public class FraudAnalysisRequestVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(FraudAnalysisRequestVerticle.class);

  private final String FRAUD_ANALYSIS_SVC = System.getenv("FRAUD_ANALYSIS_SVC");

  @Override
  public void start() {
    final EventBus eventBus = this.vertx.eventBus();
    eventBus.consumer("fraud.analysis.request").toObservable().subscribe(message ->{
      final FraudAnalysisRequest fraudAnalysisRequest = Json.decodeValue(message.body().toString(), FraudAnalysisRequest.class);
      LOGGER.info("Executing fraud analysis for order id: " + fraudAnalysisRequest.getOrder().getId());
      final WebClient webClient = WebClient.create(this.vertx);
      webClient.postAbs(FRAUD_ANALYSIS_SVC).rxSendJson(message.body());
    });
  }

}
