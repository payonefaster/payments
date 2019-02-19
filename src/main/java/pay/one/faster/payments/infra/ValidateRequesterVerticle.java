package pay.one.faster.payments.infra;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.client.WebClient;

public class ValidateRequesterVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    final WebClient webClient = WebClient.create(this.vertx);




  }
}
