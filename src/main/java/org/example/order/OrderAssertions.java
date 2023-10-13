package org.example.order;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {
    public void orderedSuccessfully (ValidatableResponse response)  {
        response.assertThat()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
        ;

    }
}
