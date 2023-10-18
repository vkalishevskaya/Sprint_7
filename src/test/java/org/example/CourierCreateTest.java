package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.junit.After;
import org.junit.Test;

public class CourierCreateTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    private int courierId;  // default value

    @After public void deleteCourier() {
        if (courierId > 0) {
            ValidatableResponse response = client.deleteCourier(courierId);
            check.deletedSuccessfully(response);
        }
    }

    @Test
    @DisplayName("create new courier")
    @Description("checking status-code")
    public void courier() {
        var courier = generator.random();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.createdSuccessfully(creationResponse);
    }

    @Test
    @DisplayName("courier data repeats")
    @Description("unable to create a new courier with non-unique data")
    public void courierRepeats(){
        var courier = generator.generic();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.alreadyExists(creationResponse);
    }

    @Test
    @DisplayName("creating without password")
    @Description("creating is unable without password")
    public void creationFails() {
        var courier = generator.generic();
        courier.setPassword(null);

        ValidatableResponse loginResponse = client.createCourier(courier);
        String message = check.creationFailed(loginResponse);
        assert !message.isBlank();
    }

    @Test
    @DisplayName("creating with repeating login")
    @Description("unable to create a new courier with non-unique login")
    public void loginAlreadyExists(){
        var courier = generator.repeats();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.alreadyExists(creationResponse);
    }

    @Test
    @DisplayName("creating without login field")
    @Description("creating is unable without login")
    public void creationWithoutLogin() {
        var courier = generator.noLogin();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.creationFailed(creationResponse);
    }

    @Test
    @DisplayName("creating without name field")
    @Description("creating is unable without name")
    public void creationWithoutName() {
        var courier = generator.noName();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.creationFailed(creationResponse);
    }
}
