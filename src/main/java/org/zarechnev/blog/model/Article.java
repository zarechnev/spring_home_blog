package org.zarechnev.blog.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private long id;

    private String author;

    private String articleTitle;

    @Column(length = 10000)
    private String articleBody;

    private LocalDateTime createdDateTime;

    @Value("false")
    private Boolean isVisible;

    @Column(length = 10000)
    private String articleHTMLBody;

    private Long viewCount;

    private Double score;

    public Double addScore(Integer oneMoreScore) {
        setScore(getScore() + oneMoreScore / 2);
        return getScore();
    }

}