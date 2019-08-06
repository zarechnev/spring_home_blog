package org.zarechnev.blog.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.zarechnev.blog.constant.Answer.SUCCESSFUL_ANSWER;
import static org.zarechnev.blog.constant.Answer.UNSUCCESSFUL_ANSWER;
import static org.zarechnev.blog.constant.ControllerPathURLs.API_URL_PATH;
import static org.zarechnev.blog.constant.ControllerPathURLs.ARTICLE_URL_PATH;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ArticleAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String correctNewArticleInJson = "{\"author\": \"Author\", \"title\": \"Title\", \"article\": \"Article\"}";
    private String inCorrectNewArticleInJson = "{\"athor\": \"Author\", \"title\": \"Title\", \"article\": \" body\"}";

    @Test
    void addArticleWithIncorrectJson() throws Throwable {
        mockMvc.perform(post(API_URL_PATH + ARTICLE_URL_PATH)
                .contentType("application/json")
                .content(inCorrectNewArticleInJson))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(UNSUCCESSFUL_ANSWER));
    }

    @Test
    void addArticleWithCorrectJson() throws Throwable {
        mockMvc.perform(post(API_URL_PATH + ARTICLE_URL_PATH)
                .contentType("application/json")
                .content(correctNewArticleInJson))
                .andExpect(status().isOk())
                .andExpect(content().string(SUCCESSFUL_ANSWER));
    }
}