package pay.one.faster.payments.domain.order;

import java.math.BigDecimal;

public class Order {

  private String id;

  private BigDecimal value;

  public String getId() {
    return id;
  }

  public BigDecimal getValue() {
    return value;
  }

}