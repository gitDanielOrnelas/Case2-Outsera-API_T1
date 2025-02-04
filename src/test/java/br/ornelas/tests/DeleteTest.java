package br.ornelas.tests;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.ornelas.core.BaseTest;
import io.restassured.response.Response;

public class DeleteTest extends BaseTest {

    public void deletePost(int postId) {
        Response response =
            given()
                .when()
                .delete("/posts/" + postId)
            .then()
                .statusCode(200)
                .extract()
                .response();

        assertEquals(0, response.body().asString().length(), "O corpo da resposta deve estar vazio");
        System.out.println("Post deletado com sucesso.");
    }
}
