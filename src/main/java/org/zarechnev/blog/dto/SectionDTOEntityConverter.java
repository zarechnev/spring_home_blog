package org.zarechnev.blog.dto;

import org.zarechnev.blog.repository.SectionEntity;

import java.util.List;

public interface SectionDTOEntityConverter {

    SectionDTO sectionEntityToDTO(SectionEntity sectionEntity);
    List<SectionDTO> sectionEntityToDTO(List<SectionEntity> sectionEntityList);

    SectionEntity sectionDTOToEntity(SectionDTO sectionDTO);
    List<SectionEntity> sectionDTOToEntity(List<SectionDTO> sectionDTOList);

}
