package com.yehor.api.services;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public abstract class BaseService {

    static {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.port = 443;
    }

    protected static <T> T doGet(String uri, int code, Class<T> type) {
        ValidatableResponse response = given()
                .get(uri)
                .then().log().all()
                .statusCode(code);

        return returnAsType(response, type);
    }

    protected static <T> T doPost(String uri, Object body, int code, Class<T> type) {
        ValidatableResponse response = given()
                .body(body)
                .post(uri)
                .then().log().all()
                .statusCode(code);

        return returnAsType(response, type);
    }

    protected static <T> T doDelete(String uri, int code, Class<T> type) {
        ValidatableResponse response = given()
                .delete(uri)
                .then().log().all()
                .statusCode(code);

        return returnAsType(response, type);
    }

    @SuppressWarnings("unchecked")
    private static <T> T returnAsType(ValidatableResponse response, Class<T> type) {
        T value;

        if (type == String.class) {
            value = (T) response.extract().body().asString();
        } else {
            value = response.extract().body().as(type);
        }

        return value;
    }
}
