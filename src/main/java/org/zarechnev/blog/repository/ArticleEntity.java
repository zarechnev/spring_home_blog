package org.zarechnev.blog.repository;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "article_body", length = 10000)
    private String articleBody;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "visible")
    @Value("false")
    private Boolean visible;

    public ArticleEntity(String articleAuthor, String articleTitle) {
        this.setAuthor(articleAuthor);
        this.setArticleTitle(articleTitle);
        this.setCreateDate(LocalDateTime.now());
    }
}