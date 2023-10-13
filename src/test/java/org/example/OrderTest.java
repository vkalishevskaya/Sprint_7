package org.example;

import io.restassured.response.ValidatableResponse;
import org.example.order.OrderAssertions;
import org.example.order.OrderClient;
import org.example.order.OrderGenerator;
import org.junit.Test;

public class OrderTest {

    private final OrderGenerator generator = new OrderGenerator();
    private final OrderClient orders = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    public void succesfullOrder() {
        var order = generator.valid();
        ValidatableResponse loginResponse = orders.create(order);
        check.orderedSuccessfully(loginResponse);
    }
    @Test
    public void blackOrder() {
        var order = generator.colorBlack();
        ValidatableResponse loginResponse = orders.create(order);
        check.orderedSuccessfully(loginResponse);
    }
    @Test
    public void greyOrder() {
        var order = generator.colorGrey();
        ValidatableResponse loginResponse = orders.create(order);
        check.orderedSuccessfully(loginResponse);
    }
    @Test
    public void bothColors() {
        var order = generator.twoColors();
        ValidatableResponse loginResponse = orders.create(order);
        check.orderedSuccessfully(loginResponse);
    }
}
