package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static RegistrationInfo registrationInfo;
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void sendRequest(RegistrationInfo registeredUser) {
        given()
                .spec(requestSpec)
                .body(new RegistrationInfo(registeredUser.getLogin(),
                        registeredUser.getPassword(),
                        registeredUser.getStatus()))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static String getRandomLogin() {
        return faker.name().firstName().toLowerCase();
    }

    public static String getRandomPassword() {
        return faker.letterify("????????");
    }

    public static RegistrationInfo getUser(String status) {
        return new RegistrationInfo(getRandomLogin(), getRandomPassword(), status);
    }

    public static RegistrationInfo getRegisteredUser(String status) {
        var registeredUser = getUser(status);
        sendRequest(registeredUser);
        return registeredUser;
    }
}
