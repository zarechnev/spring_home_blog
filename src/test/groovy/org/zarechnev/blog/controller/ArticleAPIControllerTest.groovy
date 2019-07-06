package org.zarechnev.blog.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.zarechnev.blog.entity.ArticleModel
import org.zarechnev.blog.repository.ArticleRepository
import spock.lang.Specification

import static org.zarechnev.blog.constant.Answer.SUCCESSFUL_ANSWER

class ArticleAPIControllerTest extends Specification {

    @Autowired
    protected MockMvc mvc

    def "test addArticle"() {
        given:
            String articleInJson =
                    "{\"author\": \"Author\"," +
                    "\"title\": \"Title\"," +
                    "\"article\": \"Article body\"}"
            ArticleRepository articleRepo
            ArticleModel article
        when:
            def results = mvc.perform(post('/registrations').contentType(APPLICATION_JSON).content(toJson(articleInJson)))
        then:
            results.toString() == SUCCESSFUL_ANSWER
    }
}
