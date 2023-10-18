package org.example;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.OrderAssertions;
import org.example.order.OrderClient;
import org.example.order.OrderGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderTest {


    private final OrderGenerator generator = new OrderGenerator();
    private final OrderClient orders = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    private List<String> color;
    public OrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] color() {
        return new Object[][] {
                {List.of("GREY", "BLACK")},
                {null},
                {List.of("BLACK")},
                {List.of("GREY")},
        };
    }

    @Test
    @DisplayName("check colors in order")
    @Description("A couple of tests")
    public void successfulOrderColors() {
        var order = generator.orderTest();
        ValidatableResponse loginResponse = orders.create(order);
        check.orderedSuccessfully(loginResponse);
    }
}