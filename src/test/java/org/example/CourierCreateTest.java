package org.example;

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
    public void courier() {
        var courier = generator.random();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.createdSuccessfully(creationResponse);
    }

    @Test
    public void courierRepeats(){
        var courier = generator.generic();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.alreadyExists(creationResponse);
    }

    @Test public void creationFails() {
        var courier = generator.generic();
        courier.setPassword(null);

        ValidatableResponse loginResponse = client.createCourier(courier);
        String message = check.creationFailed(loginResponse);
        assert !message.isBlank();
    }

    @Test
    public void loginAlreadyExists(){
        var courier = generator.repeats();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.alreadyExists(creationResponse);
    }

    @Test public void creationWithoutLogin() {
        var courier = generator.noLogin();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.creationFailed(creationResponse);
    }

    @Test public void creationWithoutPassword() {
        var courier = generator.noPassword();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.creationFailed(creationResponse);
    }

    @Test public void creationWithoutName() {
        var courier = generator.noName();
        ValidatableResponse creationResponse = client.createCourier(courier);
        check.creationFailed(creationResponse);
    }
}
