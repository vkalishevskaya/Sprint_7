package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("successful order test")
    @Description("checking status-code")
    public void successfulOrder() {
        ValidatableResponse loginResponse = orders.create();
        check.orderesGetSuceesfully(loginResponse);
    }
}
