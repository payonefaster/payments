package pay.one.faster.payments.infra;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.Message;
import java.time.LocalDateTime;
import pay.one.faster.payments.domain.PaymentId;
import pay.one.faster.payments.domain.PaymentRequest;
import pay.one.faster.payments.domain.authorization.TransactionRequest;
import pay.one.faster.payments.domain.fraud.FraudAnalysisRequest;
import pay.one.faster.payments.domain.requester.CheckRequestEligibility;
import rx.Observable;

/**
 * @author claudioed on 2019-02-23.
 * Project payments
 */
public class ProcessPaymentRequestVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPaymentRequestVerticle.class);

  @Override
  public void start() {
    this.vertx.eventBus().consumer("transaction.request").toObservable().subscribe(request ->{
      final PaymentRequest paymentRequest = Json.decodeValue(request.body().toString(), PaymentRequest.class);
      paymentRequest.at(LocalDateTime.now());

      final PaymentId paymentId =
          new PaymentId(
              paymentRequest.getCard().getNumber(),
              paymentRequest.getOrder().getValue(),
              paymentRequest.getAt(),
              paymentRequest.getType());

      final String requesterId = request.headers().get("requester-id");

      DeliveryOptions deliveryOptions = new DeliveryOptions();
      deliveryOptions.addHeader("payment-id",paymentId.id());


      final CheckRequestEligibility checkRequestEligibility = new CheckRequestEligibility(
          requesterId);

      final FraudAnalysisRequest fraudAnalysisRequest = new FraudAnalysisRequest(
          paymentRequest.getAddress(), paymentRequest.getOrder(), paymentRequest.getCustomer());

      final TransactionRequest authorizationRequest = new TransactionRequest(
          paymentRequest.getCard().getNumber(), paymentRequest.getOrder().getValue(),
          LocalDateTime.now(), paymentRequest.getType());

      final Observable<Message<String>> checkRequester = this.vertx.eventBus().<String>rxSend(
          "requester.check.able", Json.encode(checkRequestEligibility),deliveryOptions).toObservable();

      final Observable<Message<String>> fraudAnalysis = this.vertx.eventBus().<String>rxSend(
          "fraud.analysis.request", Json.encode(fraudAnalysisRequest),deliveryOptions).toObservable();

      final Observable<Message<String>> authorization = this.vertx.eventBus().<String>rxSend(
          "authorization.request", Json.encode(authorizationRequest),deliveryOptions).toObservable();

      checkRequester.flatMap(requester -> {


        return Observable.just(paymentRequest);
      }).flatMap(paymentRequest1 -> {

        return fraudAnalysis;
      }).flatMap(fraud ->{

        return authorization;
      }).flatMap(auth->{

        return Observable.just(paymentRequest);
      }).doOnError(ex -> {

      });


    });
  }

}
