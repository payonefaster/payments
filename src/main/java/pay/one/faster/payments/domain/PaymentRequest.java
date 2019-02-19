package pay.one.faster.payments.domain;

import pay.one.faster.payments.domain.customer.Customer;
import pay.one.faster.payments.domain.customer.Place;
import pay.one.faster.payments.domain.order.Card;
import pay.one.faster.payments.domain.order.Order;

public class PaymentRequest {

    private Order order;

    private Place address;

    private Card card;

    private Customer customer;

    public Order getOrder() {
        return order;
    }

    public Place getAddress() {
        return address;
    }

    public Card getCard() {
        return card;
    }

    public Customer getCustomer() {
        return customer;
    }
}
