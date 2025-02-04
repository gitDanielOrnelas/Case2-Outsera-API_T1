package br.ornelas.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import br.ornelas.core.BaseTest;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class UpdateTest extends BaseTest {
	
	PostTest postId = new PostTest();

	@Test
	public void testUpdatePost() {
		Map<String, Object> updateData = new HashMap<>();
		updateData.put("title", "Título Atualizado");
		updateData.put("body", "Conteúdo atualizado do post");
		updateData.put("userId", 1);

		Response response = 
			given().contentType(ContentType.JSON)
				.body(updateData)
			.when()
				.put("/posts/" + postId)
			.then()
				.statusCode(200)
				.extract().response();

		String updatedTitle = response.jsonPath().getString("title");
		assertEquals("Título Atualizado", updatedTitle);
		System.out.println("Título atualizado para: " + updatedTitle);
	}
}
