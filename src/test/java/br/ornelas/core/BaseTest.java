package br.ornelas.core;

import io.restassured.RestAssured;

public class BaseTest {

    static {
        setup();
    }

    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
}
