package org.example.order;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {
    public void orderedSuccessfully (ValidatableResponse response)  {
        response.assertThat()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
        ;
    }
    public void orderesGetSuceesfully (ValidatableResponse response)  {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue())
        ;
    }
}
