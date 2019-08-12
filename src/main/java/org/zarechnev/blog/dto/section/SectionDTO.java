package org.zarechnev.blog.dto.section;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SectionDTO {

    private String section;

    public SectionDTO(String section) {
        this.section = section;
    }
}
