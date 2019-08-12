package org.zarechnev.blog.dto.section;

import java.util.List;

public interface DTOSectionService {
    List<SectionDTO> findAll();

    SectionDTO save(SectionDTO section);
}
