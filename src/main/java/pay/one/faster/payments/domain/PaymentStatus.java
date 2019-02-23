package pay.one.faster.payments.domain;

/**
 * @author claudioed on 2019-02-23.
 * Project payments
 */
public enum PaymentStatus {

  INVALID_REQUESTER,

  BLOCKED_REQUESTER,

  VALID_REQUESTER,

  DENIED_BY_INSUFICCIENT_FUNDS,

  INVALID_CARD,

  FRAUD,

  APPROVED

}
