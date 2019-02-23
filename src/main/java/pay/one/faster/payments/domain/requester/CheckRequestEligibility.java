package pay.one.faster.payments.domain.requester;

/**
 * @author claudioed on 2019-02-23.
 * Project payments
 */
public class CheckRequestEligibility {

  private String requesterId;

  public CheckRequestEligibility(){}

  public CheckRequestEligibility(String requesterId) {
    this.requesterId = requesterId;
  }

  public String getRequesterId() {
    return requesterId;
  }

  public void setRequesterId(String requesterId) {
    this.requesterId = requesterId;
  }

}
