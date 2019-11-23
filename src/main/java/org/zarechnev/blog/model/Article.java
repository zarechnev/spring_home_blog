package org.zarechnev.blog.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
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

}