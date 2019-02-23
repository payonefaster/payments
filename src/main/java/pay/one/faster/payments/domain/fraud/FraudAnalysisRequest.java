package pay.one.faster.payments.domain.fraud;

import pay.one.faster.payments.domain.customer.Customer;
import pay.one.faster.payments.domain.customer.Place;
import pay.one.faster.payments.domain.order.Order;

public class FraudAnalysisRequest {

  private Place place;

  private Order order;

  private Customer customer;

  public FraudAnalysisRequest(){}

  public FraudAnalysisRequest(Place place, Order order,
      Customer customer) {
    this.place = place;
    this.order = order;
    this.customer = customer;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Place getPlace() {
    return place;
  }

  public Order getOrder() {
    return order;
  }

  public Customer getCustomer() {
    return customer;
  }
}