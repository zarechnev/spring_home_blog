package org.zarechnev.blog.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleFullDTO {
    private Long id;
    private String author;
    private String articleTitle;
    private LocalDateTime createdDatedTime;
    private String articleHTMLBody;
}
