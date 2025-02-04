package br.ornelas.tests;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import br.ornelas.core.BaseTest;

public class PutTest extends BaseTest {

    public void updatePost(int postId) {
        Map<String, Object> updatedPost = new HashMap<>();
        updatedPost.put("title", "Post Atualizado");
        updatedPost.put("body", "Conteúdo atualizado via API");
        updatedPost.put("userId", 1);

        Response response =
            given()
                .contentType(ContentType.JSON)
                .body(updatedPost)
            .when()
                .put("/posts/" + postId)
            .then()
                .statusCode(200)
                .extract()
                .response();

        assertEquals("Post Atualizado", response.jsonPath().getString("title"));
        System.out.println("Post atualizado com sucesso.");
    }
}
