package tests;

import Specification.spec.ApiRequestSpecification;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import models.login.ForLoginRequest;
import models.login.ForLoginResponse;
import models.registration.ForRegistrationUserRequest;
import models.registration.ForRegistrationUserResponse;
import models.singleResource.SingleResourceMain;
import models.users.ForUsersCreateRequest;
import models.users.ForUsersCreateResponse;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Owner("olgakos")
@Story("https://reqres.in")
@Feature("https://reqres.in")

public class ReqresTests extends ApiRequestSpecification {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

@Test
    @Tag("POST")
    @DisplayName("Cоздание пользователя")
    void postCreateUser() {
        ForUsersCreateRequest createRequest = new ForUsersCreateRequest();  //модель: users/Lombok
        createRequest.setJob("leader");
        createRequest.setName("morpheus");

        String expectedName = "morpheus";
        String expectedJob = "leader";

        step("Убедиться, что пользватель создан", () -> {
            ForUsersCreateResponse createResponse =
                    given()
                            .spec(requestReqresSpec)
                            .body(createRequest)
                            .when()
                            .post("/api/users")
                            .then()
                            .statusCode(201)
                            .extract().as(ForUsersCreateResponse.class); //модель: users/Lombok

            assertEquals(expectedName, createResponse.getName());
            assertEquals(expectedJob, createResponse.getJob());
            assertNotNull(createResponse.getId());
            assertNotNull(createResponse.getCreatedAt());
            System.out.println(createResponse);
        });
    }

    @Test
    @Tag("POST")
    @DisplayName("Авторизация")
    void successfullLogin() {

        ForLoginRequest forLogin = new ForLoginRequest(); //модель: login/Lombok
        forLogin.setEmail("eve.holt@reqres.in");
        forLogin.setPassword("cityslicka");

        step("Проверить корректный залогин", () -> {
            ForLoginResponse loginResponse =
                    given()
                            .spec(requestReqresSpec)
                            .body(forLogin)
                            .when()
                            .post("/api/login")
                            .then()
                            .statusCode(200)
                            .extract().as(ForLoginResponse.class); //модель: login/Lombok

            assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()); //токен!
        });
    }

    @Test
    @Tag("POST")
    @DisplayName("Проверить текст ошибки: нет пароля")
    void unSuccessfullLogin() {

        ForLoginRequest forLogin = new ForLoginRequest();
        forLogin.setEmail("eve.holt@reqres.in");

        step("Проверка текста ошибки если пользователь не ввел пароль", () -> {
            ForLoginResponse loginResponse =
                    given()
                            .spec(requestReqresSpec)
                            .body(forLogin)
                            .when()
                            .post("/api/login")
                            .then()
                            .statusCode(400)
                            .extract().as(ForLoginResponse.class);

            assertEquals("Missing password", loginResponse.getError());
        });
    }

    @Test
    @Tag("POST")
    @DisplayName("Корректная рега пользователя")
    void successfulRegistrationUser() {

        ForRegistrationUserRequest request = new ForRegistrationUserRequest(); //модель: registration/Lombok
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");

        step("Регистрация пользователя", () -> {
            ForRegistrationUserResponse response =
                    given()
                            .spec(requestReqresSpec)
                            .body(request)
                            .when()
                            .post("/api/register")
                            .then()
                            .statusCode(200)
                            .extract().as(ForRegistrationUserResponse.class); //модель: registration/Lombok

            assertEquals("4", response.getId());
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken()); //токен!
        });
    }

    @Test
    @Tag("POST")
    @Description("Проверка текста ошибки если пароль не POST-отправлен")
    @DisplayName("Регистрация невозможна. Нет пароля.")
    void usSuccessfulRegistrationUser() {

        ForRegistrationUserRequest request = new ForRegistrationUserRequest(); //модель: registration/Lombok
        request.setEmail("eve.holt@reqres.in");

        String expectedError = "Missing password"; //Ожидаемый результат

        step("Регистрация пользователя", () -> {
            ForRegistrationUserResponse response =
                    given()
                            .spec(requestReqresSpec)
                            .body(request)
                            .when()
                            .post("/api/register")
                            .then()
                            .statusCode(400)
                            .extract().as(ForRegistrationUserResponse.class); //модель: registration/Lombok

            assertEquals(expectedError, response.getError());
        });
    }

    @Disabled ("Пример пропущенного теста")
    @Test
    @Tag("POST")
    @Description("Этот тест не будет запущен")
    @DisplayName("Проверка текста ошибки если пароль не введен")
    void negativeLogin() {

        Map<String, String> data = new HashMap<>();
        data.put("email", "eve.holt@tests.reqres.in");

        given()
                .spec(requestReqresSpec)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @Description("Пример упавшего теста")
    @DisplayName("Статус-код 201 = fail")
    @Tag("POST")
    void negativeLoginNegative() {

        Map<String, String> data = new HashMap<>();
        data.put("email", "eve.holt@tests.reqres.in");

        given()
                .spec(requestReqresSpec)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(201)
                .body("error", is("Missing password"));
    }

    @Test
    @Tag("GET")
    @Description("Проверка что полученны ожидаемые данные")
    @DisplayName("Получаем ресурсы, данные через GET")
    void singleResource() {

        int expectedId = 2;
        String expectedName = "fuchsia rose";
        int expectedYear = 2001;
        String expectedColor = "#C74375";
        String expectedPantoneValue = "17-2031";
        String expectedUrl = "https://reqres.in/#support-heading";
        String expectedText = "To keep ReqRes free, contributions towards server costs are appreciated!";

        step("Проверяем получение ресурсов", () -> {
            SingleResourceMain singleResourceMain =
                    given()
                            .spec(requestReqresSpec)
                            .when()
                            .get("/api/unknown/2")
                            .then()
                            .spec(responseSpecification)
                            .statusCode(200)
                            .extract().as(SingleResourceMain.class);

            assertEquals(expectedId, singleResourceMain.getData().getId());
            assertEquals(expectedName, singleResourceMain.getData().getName());
            assertEquals(expectedYear, singleResourceMain.getData().getYear());
            assertEquals(expectedColor, singleResourceMain.getData().getColor());
            assertEquals(expectedPantoneValue, singleResourceMain.getData().getPantoneValue());
            assertEquals(expectedUrl, singleResourceMain.getSupport().getUrl());
            assertEquals(expectedText, singleResourceMain.getSupport().getText());
        });
    }

    @Test
    @Tag("GET")
    @DisplayName("Проверить текст в support.text")
    void getSingleUsers() {
        given()
                .spec(requestReqresSpec);
        String response =
                get("/api/users/2")
                        .then()
                        .statusCode(200)
                        .extract().path("support.text");

        assertThat(response).isEqualTo("To keep ReqRes free, contributions towards server costs are appreciated!");
    }

    @Test
    @Tag("GET")
    @DisplayName("Пользователь не найден. Код 404")
    void singleUserNotFoundTest() {
        given()
                .spec(requestReqresSpec)
                .when()
                .get("/api/users/23")
                .then()
                .statusCode(404)
                .body(is("{}"));
    }

    @Test
    @Tag("GET")
    @DisplayName("Страницы. Проверить кол-во и номер актуальной")
    void getListUsers() {
        given()
                .spec(requestReqresSpec)
                .when()
                .get("/api/users?page=2")
                .then()
                .spec(responseSpecification)
                .body("page", is(2))
                .body("total_pages", is(2))
                .assertThat()
                .statusCode(200);
    }
}