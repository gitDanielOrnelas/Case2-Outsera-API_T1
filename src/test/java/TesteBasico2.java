import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class TesteBasico2 {

	@Test
	public void testGetBooking() {
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		// Configura e executa a requisição GET para o endpoint "/booking/"
		given() // Define as configurações da requisição (headers, parâmetros, etc.)
				.header("Accept", "*/*") // adiciona o header accept
		.when() // Indica o início da execução da requisição
				.get("/posts/") // Especifica o endpoint a ser chamado
		.then() // Define as validações da resposta
				.statusCode(200)
				.log().all(); // imprimir
	}

}
