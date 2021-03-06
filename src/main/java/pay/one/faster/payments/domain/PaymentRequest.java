package pay.one.faster.payments.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import pay.one.faster.payments.domain.customer.Customer;
import pay.one.faster.payments.domain.customer.Place;
import pay.one.faster.payments.domain.order.Card;
import pay.one.faster.payments.domain.order.Order;

public class PaymentRequest {

  private Order order;

  private Place address;

  private Card card;

  private Customer customer;

  private String type;

  @JsonIgnore
  private LocalDateTime at;

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Place getAddress() {
    return address;
  }

  public void setAddress(Place address) {
    this.address = address;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public LocalDateTime getAt() {
    return at;
  }

  public void setAt(LocalDateTime at) {
    this.at = at;
  }

  public PaymentRequest at(LocalDateTime date){
    this.at = date;
    return this;
  }

}
