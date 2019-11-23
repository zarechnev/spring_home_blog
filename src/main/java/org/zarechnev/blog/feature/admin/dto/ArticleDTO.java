package org.zarechnev.blog.feature.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    Long id;
    String author;
    String articleTitle;
    LocalDateTime createdDateTime;
    Boolean isVisible;
}