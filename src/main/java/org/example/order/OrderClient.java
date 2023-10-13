package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.example.Client;

public class OrderClient extends Client {
    static final String ORDER_PATH = "/orders";

    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }
}
