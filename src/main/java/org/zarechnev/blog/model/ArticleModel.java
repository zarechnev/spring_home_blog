package org.zarechnev.blog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;

/**
 * DB entity for article.
 */
@Entity
public class ArticleModel {
    private static final int SHORT_ARTICLE_LENGTH = 300;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter @Getter
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
     * Instantiates a new Article model.
     */
    public ArticleModel() {
    }

    /**
     * Instantiates a new Article model.
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
    // TODO: Think about good view on site.
    public LocalDateTime getLastEditDateTime() {
        ZoneId zoneId = ZoneId.systemDefault();
        if (this.editedDate != null && this.editedDate.atZone(zoneId).toEpochSecond() > this.createDate.atZone(zoneId).toEpochSecond())
            return this.editedDate;
        else return this.createDate;
    }

    /**
     * Return shot path of article to show on main page.
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