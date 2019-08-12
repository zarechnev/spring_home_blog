package org.zarechnev.blog.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zarechnev.blog.entity.Article;
import org.zarechnev.blog.repository.ArticleRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
class ArticleAPIControllerTest {

    @Mock
    private ArticleRepository articleRepo;

    @InjectMocks
    private ArticleAPIController controller;

    private MockMvc mockMvc;

    private String correctNewArticleInJson = "{\"author\": \"Author\", \"title\": \"Title\", \"article\": \"Article\"}";
    private String inCorrectNewArticleInJson = "{\"athor\": \"Author\", \"title\": \"Title\", \"article\": \" body\"}";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    void addArticleWithIncorrectJson() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/article", correctNewArticleInJson);
        Mockito.when(articleRepo.save(new Article())).thenReturn(new Article());

        mockMvc.perform(builder)
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Unsuccessful!"));

//        mockMvc.perform(post("/api/article")
//                .contentType("application/json")
//                .content(inCorrectNewArticleInJson))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string("Unsuccessful!"));
    }

//    @Test
//    void addArticleWithCorrectJson() throws Throwable {
//        mockMvc.perform(post("/api/article")
//                .contentType("application/json")
//                .content(correctNewArticleInJson))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Successful!"));
//    }
}