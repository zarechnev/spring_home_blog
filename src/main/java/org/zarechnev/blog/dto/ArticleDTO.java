package org.zarechnev.blog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleDTO {

    private Long id;

    private String author;

    private String articleTitle;

    private String articleBody;

    private LocalDateTime createdDatetime;

    private Boolean visible;

    private String abstraction;

}
