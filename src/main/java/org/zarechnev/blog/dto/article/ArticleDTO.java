package org.zarechnev.blog.dto.article;

import lombok.Data;

@Data
public class ArticleDTO {

    private String articleBody;

    private String articleTitle;

    private String articleAuthor;

    public ArticleDTO(String articleAuthor, String articleTitle) {
        this.articleAuthor = articleAuthor;
        this.articleTitle = articleTitle;
    }
}
