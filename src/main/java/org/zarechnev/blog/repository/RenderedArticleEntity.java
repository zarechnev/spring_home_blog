package org.zarechnev.blog.repository;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenderedArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long articleId;

    @Column(length = 10000)
    private String articleBodyInHTML;

}