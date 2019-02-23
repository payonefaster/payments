package pay.one.faster.payments.domain.authorization;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequest {

  private String cardNumber;

  private BigDecimal value;

  private LocalDateTime at;

  private String type;

  public TransactionRequest(){}

  public TransactionRequest(String cardNumber, BigDecimal value, LocalDateTime at,
      String type) {
    this.cardNumber = cardNumber;
    this.value = value;
    this.at = at;
    this.type = type;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public BigDecimal getValue() {
    return value;
  }

  public LocalDateTime getAt() {
    return at;
  }

  public String getType() {
    return type;
  }

}
