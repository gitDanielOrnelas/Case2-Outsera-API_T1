import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.ornelas.core.BaseTest;
import io.restassured.http.ContentType;

public class ApiValidationTest extends BaseTest {

	@BeforeAll
	public static void setupApi() {
		baseURI = "https://jsonplaceholder.typicode.com"; // URL da API
	}

	@Test
	public void testGetPosts() {
		given().contentType(ContentType.JSON).when().get("/posts").then().statusCode(200) // Verifica se a resposta é
																							// 200 OK
				.header("Content-Type", containsString("application/json")) // Verifica o header
				.body("size()", greaterThan(0)) // Verifica se há posts retornados
				.body("[0].userId", notNullValue()) // Garante que o primeiro post tenha um userId
				.body("[0].title", notNullValue()); // Garante que o primeiro post tenha um título
	}

	@Test
	public void testCreatePost() {
		given().contentType(ContentType.JSON)
				.body("{ \"title\": \"Novo Post\", \"body\": \"Conteúdo do post\", \"userId\": 1 }").when()
				.post("/posts").then().statusCode(201) // Verifica se a criação do post foi bem-sucedida
				.body("id", notNullValue()) // Garante que um ID foi gerado
				.body("title", equalTo("Novo Post")) // Verifica se o título está correto
				.body("body", equalTo("Conteúdo do post")); // Verifica o corpo do post
	}

	@Test
	public void testUpdatePost() {
		given().contentType(ContentType.JSON).body(
				"{ \"id\": 1, \"title\": \"Título Atualizado\", \"body\": \"Conteúdo atualizado\", \"userId\": 1 }")
				.when().put("/posts/1").then().statusCode(200) // Atualização bem-sucedida
				.body("title", equalTo("Título Atualizado")) // Verifica se o título foi atualizado
				.body("body", equalTo("Conteúdo atualizado")); // Verifica se o corpo foi atualizado
	}

	@Test
	public void testDeletePost() {
		given().when().delete("/posts/1").then().statusCode(200); // JSONPlaceholder retorna 200 para DELETE, outras
																	// APIs podem retornar 204
	}
}
