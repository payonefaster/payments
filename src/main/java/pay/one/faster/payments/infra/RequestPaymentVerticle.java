package pay.one.faster.payments.infra;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

public class RequestPaymentVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(RequestPaymentVerticle.class);

  @Override
  public void start() {
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.post("/api/payments").handler(handler ->{
      DeliveryOptions options = new DeliveryOptions();
      final EventBus eventBus = vertx.eventBus();
      eventBus.rxSend("transaction.request",handler.getBodyAsJson(),options).toObservable().map(response ->{
        return null;
      });
    });
    final HttpServer httpServer = vertx.createHttpServer();
    httpServer.requestHandler(router::accept).listen(8080);
  }

}