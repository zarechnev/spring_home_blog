package org.zarechnev.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;

/**
 * DB entity for article.
 */
@Entity
@NoArgsConstructor
public class ArticleModel {
    private static final int SHORT_ARTICLE_LENGTH = 300;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "author")
    @Setter @Getter
    private String author;

    @Column(name = "article_title")
    @Setter @Getter
    private String articleTitle;

    @Column(name = "article_body", length = 10000)
    @Setter @Getter
    private String articleBody;

    @Column(name = "create_date")
    @Setter @Getter
    private LocalDateTime createDate;

    @Column(name = "edited_date")
    @Setter @Getter
    private LocalDateTime editedDate;

    @Column(name = "visible")
    @Setter @Getter
    private Boolean visible = true;

    /**
     * Instantiates a new Article entity.
     *
     * @param articleAuthor the article author
     * @param articleTitle  the article title
     */
    public ArticleModel(String articleAuthor, String articleTitle) {
        this.author = articleAuthor;
        this.articleTitle = articleTitle;
        this.createDate = LocalDateTime.now();
    }

    /**
     * Return DateTime of last edit.
     *
     * @return last edit date time
     */
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
        return this.articleBody.substring(0, this.SHORT_ARTICLE_LENGTH) + " . . .";
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.author, this.articleTitle);
    }
}