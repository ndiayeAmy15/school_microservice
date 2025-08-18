package com.isiexamen.school.mapping;

import com.isiexamen.school.dto.Classes;
import com.isiexamen.school.dto.Sectors;
import com.isiexamen.school.entities.SectorsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface SectorsMapper {
    Sectors toSectors(SectorsEntity sectorsEntity);
    SectorsEntity fromSectors(Sectors sectors);
}
