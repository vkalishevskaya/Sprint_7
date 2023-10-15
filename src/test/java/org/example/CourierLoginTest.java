package org.example;

import io.restassured.response.ValidatableResponse;
import org.example.courier.CourierAssertions;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.example.courier.Credentials;
import org.junit.After;
import org.junit.Test;

import java.util.Map;

public class CourierLoginTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    private int courierId;

    @After
    public void deleteCourier() {
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

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);

        assert courierId > 100;
    }

    @Test public void loginFails() {
        ValidatableResponse loginResponse = client.login(Map.of("password", "null"));
        check.loginFailed(loginResponse);
    }

    @Test public void loginWithoutPassword() {
        ValidatableResponse loginResponse = client.login(Map.of("login", "login"));
        check.loginFailed(loginResponse);
    }

    @Test public void incorrectPassword() {
        var courier = generator.repeats();
        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        check.notFound(loginResponse);
    }

    @Test
    public void userNotExist(){
        var courier = generator.notExist();
        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        check.notFound(loginResponse);
    }
}
