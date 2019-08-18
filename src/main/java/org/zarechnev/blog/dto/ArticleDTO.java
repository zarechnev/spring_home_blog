package org.zarechnev.blog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleDTO {

    private String body;

    private String title;

    private String author;

    private LocalDateTime createDate;

    private LocalDateTime editedDate;

    private Boolean visible;

    private String abstraction;

}
