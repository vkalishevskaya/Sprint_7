package org.example;

import io.restassured.response.ValidatableResponse;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.example.courier.Credentials;
import org.junit.After;
import org.junit.Test;

import java.util.Map;

public class CourierCreateTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    private int courierId;  // default value

    @After public void deleteCourier() {
        if (courierId > 0) {
            ValidatableResponse response = client.delete(courierId);
            check.deletedSuccessfully(response);
        }
    }

    @Test
    public void courier() {
        var courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);
    }

    @Test
    public void courierRepeats(){
        var courier = generator.generic();
        ValidatableResponse creationResponse = client.create(courier);
        check.alreadyExists(creationResponse);
    }

    @Test public void creationFails() {
        var courier = generator.generic();
        courier.setPassword(null);

        ValidatableResponse loginResponse = client.create(courier);
        String message = check.creationFailed(loginResponse);
        assert !message.isBlank();
    }

    @Test
    public void loginAlreadyExists(){
        var courier = generator.repeats();
        ValidatableResponse creationResponse = client.create(courier);
        check.alreadyExists(creationResponse);
    }
    @Test public void creationWithoutLogin() {
            var courier = generator.noLogin();
            ValidatableResponse creationResponse = client.create(courier);
            check.creationFailed(creationResponse);
        }
    @Test public void creationWithoutPassword() {
        var courier = generator.noPassword();
        ValidatableResponse creationResponse = client.create(courier);
        check.creationFailed(creationResponse);
    }
    @Test public void creationWithoutName() {
        var courier = generator.noName();
        ValidatableResponse creationResponse = client.create(courier);
        check.creationFailed(creationResponse);
    }
}
