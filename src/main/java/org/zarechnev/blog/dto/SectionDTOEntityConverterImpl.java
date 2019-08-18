package org.zarechnev.blog.dto;

import org.springframework.stereotype.Service;
import org.zarechnev.blog.repository.SectionEntity;

import java.util.List;

@Service
public class SectionDTOEntityConverterImpl implements SectionDTOEntityConverter {

    @Override
    public SectionDTO sectionEntityToDTO(SectionEntity sectionEntity) {
        return null;
    }

    @Override
    public List<SectionDTO> sectionEntityToDTO(List<SectionEntity> sectionEntityList) {
        return null;
    }

    @Override
    public SectionEntity sectionDTOToEntity(SectionDTO sectionDTO) {
        return null;
    }

    @Override
    public List<SectionEntity> sectionDTOToEntity(List<SectionDTO> sectionDTOList) {
        return null;
    }

}
