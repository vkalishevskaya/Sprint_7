package org.example;
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
    public void succesfullOrder() {
        var order = generator.orderTest();
        ValidatableResponse loginResponse = orders.create(order);
        check.orderedSuccessfully(loginResponse);
    }
}