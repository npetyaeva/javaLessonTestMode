package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@Data
@NoArgsConstructor
public class DataGenerator {
    private RegistrationInfo registrationInfo;
    private final Faker faker = new Faker(new Locale("en"));

    private final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public void sendRequest(RegistrationInfo registeredUser) {
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

    public String getRandomLogin() {
        return faker.name().firstName().toLowerCase();
    }

    public String getRandomPassword() {
        return faker.letterify("????????");
    }

    public RegistrationInfo getUser(String status) {
        var newUser = new RegistrationInfo(getRandomLogin(), getRandomPassword(), status);
        return newUser;
    }

    public RegistrationInfo getRegisteredUser(String status) {
        var registeredUser = getUser(status);
        sendRequest(registeredUser);
        return registeredUser;
    }
}
