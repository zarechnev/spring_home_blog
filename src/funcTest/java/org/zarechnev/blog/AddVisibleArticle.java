package org.zarechnev.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zarechnev.blog.admin.dto.ArticleFullDTO;
import org.zarechnev.blog.repository.ArticleRepository;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddVisibleArticle {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    ArticleRepository articleRepository;

    private ObjectMapper mapper = new ObjectMapper();
    private MockMvc mockMvc;

    private String addArticle() throws Exception {
        ArticleFullDTO article = ArticleFullDTO.builder()
                .author("authoRR")
                .articleBody("An h1 header\\n============\\n\\nParagraphs are separated by a blank line.\\n\\n2nd paragraph. *Italic*, **bold**, and `monospace`.")
                .articleTitle("titlEE")
                .isVisible(true)
                .build();

        String articleInJSON = mapper.writeValueAsString(article);

        MockHttpServletRequestBuilder content = post("/api/article")
                .contentType("application/json")
                .content(articleInJSON);

        return mockMvc.perform(content)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        articleRepository.deleteAll();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void validateAddingResponse() throws Exception {
        // when
        String response = addArticle();
        ArticleFullDTO articleFromResponse = mapper.readValue(response, ArticleFullDTO.class);

        // then
        assertEquals(articleFromResponse.getArticleHTMLBody(), "<p>An h1 header\\n============\\n\\nParagraphs are separated by a blank line.\\n\\n2nd paragraph. <em>Italic</em>, <strong>bold</strong>, and <code>monospace</code>.</p>\n");
        assertEquals(articleFromResponse.getAuthor(), "authoRR");
        assertEquals(articleFromResponse.getArticleBody(), "An h1 header\\n============\\n\\nParagraphs are separated by a blank line.\\n\\n2nd paragraph. *Italic*, **bold**, and `monospace`.");
        assertEquals(articleFromResponse.getArticleTitle(), "titlEE");
        assertNotNull(articleFromResponse.getCreatedDateTime());
        assertNotNull(articleFromResponse.getId());
    }

    @Test
    public void isArticleOnMainPage() throws Exception {
        // given
        addArticle();

        // when
        String response = mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        assertTrue(response.contains("<p><p>An h1 header\\n============\\n\\nParagraphs are separated by a blank line.\\n\\n2nd paragraph. <em>Italic</em>, <strong>bold</st ...</p>"));
    }

    @Test
    public void articleOnSelfPage() throws Exception {
        // given
        String addingResponse = addArticle();
        ArticleFullDTO articleFromResponse = mapper.readValue(addingResponse, ArticleFullDTO.class);
        Long articleId = articleFromResponse.getId();
        String articleUrl = "/article/" + articleId;

        // when
        String response = mockMvc.perform(get(articleUrl))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        assertTrue(response.contains("<p><p>An h1 header\\n============\\n\\nParagraphs are separated by a blank line.\\n\\n2nd paragraph. <em>Italic</em>, <strong>bold</strong>, and <code>monospace</code>.</p>"));
    }

    @Test
    public void articleInJSONViaAPI() throws Exception {
        // given
        String addingResponse = addArticle();
        ArticleFullDTO addedArticle = mapper.readValue(addingResponse, ArticleFullDTO.class);
        Long articleId = addedArticle.getId();
        String articleUrl = "/api/article/" + articleId;

        // when
        String response = mockMvc.perform(get(articleUrl))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        ArticleFullDTO articleFromResponse = mapper.readValue(response, ArticleFullDTO.class);
        assertEquals(articleFromResponse, addedArticle);
    }

}
