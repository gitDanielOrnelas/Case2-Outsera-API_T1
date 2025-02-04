package br.ornelas.tests;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import br.ornelas.core.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostTest extends BaseTest {
    
    public int createPost() {
        Map<String, Object> postData = new HashMap<>();
        postData.put("title", "Novo Post Test Daniel");
        postData.put("body", "Conteúdo do post criado via API");
        postData.put("userId", 1);

        Response response =
            given()
                .contentType(ContentType.JSON)
                .body(postData)
            .when()
                .post("/posts/")
            .then()
                .statusCode(201)
                .extract()
                .response();

        int postId = response.jsonPath().getInt("id");
        assertTrue(postId > 0, "ID deve ser maior que 0");
        System.out.println("Post criado com ID: " + postId);
        
        return postId; // Retorna o ID para ser usado nos outros testes
    }
}
