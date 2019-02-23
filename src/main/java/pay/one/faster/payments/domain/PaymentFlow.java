package pay.one.faster.payments.domain;

/** @author claudioed on 2019-02-23. Project payments */
public class PaymentFlow {

  private String paymentId;

  private PaymentStatus status;

  public PaymentFlow() {}

  public PaymentFlow(String paymentId, PaymentStatus status) {
    this.paymentId = paymentId;
    this.status = status;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public String getPaymentId() {
    return paymentId;
  }
}
