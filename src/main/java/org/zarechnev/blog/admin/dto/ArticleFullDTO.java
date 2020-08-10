package org.zarechnev.blog.admin.dto;

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
    Long id;
    String author;
    String articleTitle;
    LocalDateTime createdDateTime;
    Boolean isVisible;
    String articleBody;
    String articleHTMLBody;
}