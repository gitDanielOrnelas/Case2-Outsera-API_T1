import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.ornelas.tests.DeleteTest;
import br.ornelas.tests.GetTest;
import br.ornelas.tests.PostTest;
import br.ornelas.tests.PutTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteCascata {

	static int postId;
	static PostTest postTests = new PostTest();
	static GetTest getTests = new GetTest();
	static PutTest putTests = new PutTest();
	static DeleteTest deleteTests = new DeleteTest();

	@Test
	@Order(1) // Criar um post primeiro
	public void testCreatePost() {
		postId = postTests.createPost();
		assertTrue(postId > 0, "ID deve ser maior que 0");
	}

	@Test
	@Order(2) // Buscar o post criado
	public void testGetPostById() {
		getTests.getPostById(postId);
	}

	@Test
	@Order(3) // Atualizar o post
	public void testUpdatePost() {
		putTests.updatePost(postId);
	}

	@Test
	@Order(4) // Deletar o post criado
	public void testDeletePost() {
		deleteTests.deletePost(postId);
	}
}