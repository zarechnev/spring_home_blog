package org.zarechnev.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * DB entity for article.
 */
@Entity
@NoArgsConstructor
public class CommentsModel {
    private static final int SHORT_COMMENT_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "author")
    @Setter @Getter
    private String author;

    @Column(name = "comment_body", length = 1000)
    @Setter @Getter
    private String commentBody;

    @Column(name = "create_date")
    @Setter @Getter
    private LocalDateTime createDate;

    @Column(name = "visible")
    @Setter @Getter
    private Boolean visible = true;

    /**
     * Instantiates a new Comment entity.
     *
     * @param commentAuthor the comment author
     * @param commentBody  the comment title
     */
    public CommentsModel(String commentAuthor, String commentBody) {
        this.author = commentAuthor;
        this.commentBody = commentBody;
        this.createDate = LocalDateTime.now();
    }

    /**
     * Return short part of article to show on main page.
     *
     * @return abstract
     */
    public String getAbstractComment() {
        return this.commentBody.substring(0, this.SHORT_COMMENT_LENGTH) + " . . .";
    }
}