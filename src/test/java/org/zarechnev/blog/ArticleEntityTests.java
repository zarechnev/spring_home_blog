package org.zarechnev.blog;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zarechnev.blog.entity.ArticleModel;
import org.zarechnev.blog.repository.ArticleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleEntityTests {
	@Autowired
	private ArticleRepository articleRepo;

	/**
	 * Test for Instiate
	 */
	@Test
	public void creatingArticle() {
		ArticleModel article = new ArticleModel("TestAuthor", "TestArticleTitle");
		articleRepo.save(article);

		Assert.assertEquals("TestAuthor", article.getAuthor());
		Assert.assertEquals("TestArticleTitle", article.getArticleTitle());
	}
}