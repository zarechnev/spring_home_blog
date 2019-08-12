package org.zarechnev.blog.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Article {
    private static final int SHORT_ARTICLE_LENGTH = 400;

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

    @Column(name = "edited_date")
    private LocalDateTime editedDate;

    @Column(name = "visible")
    private Boolean visible = true;

    public Article(String articleAuthor, String articleTitle) {
        this.setAuthor(articleAuthor);
        this.setArticleTitle(articleTitle);
        this.setCreateDate(LocalDateTime.now());
    }

    public Article(String articleAuthor, String articleTitle, String articleBody) {
        this(articleAuthor, articleTitle);
        this.setArticleBody(articleBody);
    }

    public LocalDateTime getLastEditDateTime() {
        // TODO: Think about good view on site.
        ZoneId zoneId = ZoneId.systemDefault();
        if (this.editedDate != null && this.editedDate.atZone(zoneId).toEpochSecond() > this.createDate.atZone(zoneId).toEpochSecond())
            return this.editedDate;
        else return this.createDate;
    }

    /**
     * Return short part of article to show on main page.
     *
     * TODO Возвращать определённое количество слов, и проверять на общюю длинну
     *
     * @return abstract
     */
    public String getAbstract() {
        return this.getArticleBody().substring(0,
                SHORT_ARTICLE_LENGTH > this.getArticleBody().length() ? this.getArticleBody().length() : SHORT_ARTICLE_LENGTH
        ) + " ...";
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.author, this.articleTitle);
    }
}