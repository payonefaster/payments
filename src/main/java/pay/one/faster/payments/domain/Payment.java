package pay.one.faster.payments.domain;

import pay.one.faster.payments.domain.customer.Customer;
import pay.one.faster.payments.domain.customer.Place;
import pay.one.faster.payments.domain.order.Card;
import pay.one.faster.payments.domain.order.Order;

public class Payment {

  private String id;

  private Order order;

  private Place address;

  private Card card;

  private Customer customer;

  private String type;

  public Payment() {
  }

  public Payment(String id, Order order, Place address,
      Card card, Customer customer, String type) {
    this.id = id;
    this.order = order;
    this.address = address;
    this.card = card;
    this.customer = customer;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
}
