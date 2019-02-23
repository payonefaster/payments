package pay.one.faster.payments.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import pay.one.faster.payments.infra.crypto.Hashing;

/**
 * @author claudioed on 2019-02-07.
 * Project payments
 */
public class PaymentId {

  private final String cardNumber;

  private final BigDecimal value;

  private final LocalDateTime at;

  private final String type;

  public PaymentId(String cardNumber, BigDecimal value, LocalDateTime at,
      String type) {
    this.cardNumber = cardNumber;
    this.value = value;
    this.at = at;
    this.type = type;
  }

  public String id(){
    return Hashing.hash(this.cardNumber + this.value + this.type + this.at.toString());
  }

}
