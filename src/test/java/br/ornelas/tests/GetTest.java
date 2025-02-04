package br.ornelas.tests;

import io.restassured.response.Response;
import br.ornelas.core.BaseTest;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class GetTest extends BaseTest {

    public void getPostById(int postId) {
        Response response =
            given()
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String title = response.jsonPath().getString("title");
        assertNotNull(title, "O título não deve ser nulo");
        System.out.println("Título do post: " + title);
    }
}
