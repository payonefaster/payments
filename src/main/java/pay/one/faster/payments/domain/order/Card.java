package pay.one.faster.payments.domain.order;

public class Card {

  private String number;

  private String holder;

  private String validUntil;

  private String cvv;

  public Card(){}

  public String getNumber() {
    return number;
  }

  public String getHolder() {
    return holder;
  }

  public String getValidUntil() {
    return validUntil;
  }

  public String getCvv() {
    return cvv;
  }
}
