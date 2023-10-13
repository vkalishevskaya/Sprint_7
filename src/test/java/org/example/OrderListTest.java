package org.example;

import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.example.order.OrderAssertions;
import org.example.order.OrderListClient;
import org.junit.Test;

public class OrderListTest {
    private final OrderListClient orders = new OrderListClient();
    private final OrderAssertions check = new OrderAssertions();



    @Test
    public void succesfullOrder() {
        ValidatableResponse loginResponse = orders.create();
        check.orderesGetSuceesfully(loginResponse);
    }
}
