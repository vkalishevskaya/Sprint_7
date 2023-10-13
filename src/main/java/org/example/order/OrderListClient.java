package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.example.Client;
import org.example.courier.Courier;
import org.example.courier.Credentials;

public class OrderListClient extends Client {
    static final String ALL_ORDERS_PATH = "/orders";

    public ValidatableResponse create() {;

        return spec()
                .when()
                .get(ALL_ORDERS_PATH)
                .then().log().all();
    }
}
