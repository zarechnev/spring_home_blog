package org.zarechnev.blog.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class CommentEntity {

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

    public CommentEntity(String commentAuthor, String commentBody) {
        this.author = commentAuthor;
        this.commentBody = commentBody;
        this.createDate = LocalDateTime.now();
    }

    public String getAbstractionComment() {
        return this.getCommentBody().substring(
                0,
                SHORT_COMMENT_LENGTH > this.getCommentBody().length() ? this.getCommentBody().length() : SHORT_COMMENT_LENGTH
        ) + " ...";
    }

}