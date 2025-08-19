package com.isiexamen.school.mapping;

import com.isiexamen.school.dto.Classes;
import com.isiexamen.school.entities.ClassesEntity;
import com.isiexamen.school.entities.SectorsEntity;
import org.mapstruct.Mapper;

//sert à demander à MapStruct de générer automatiquement une classe d’implémentation de ton interface Mapper et de l’enregistrer comme bean Spring.
@Mapper(componentModel = "spring")
public interface ClassesMapper {
    // prend une entité JPA (venant de la BDD) et la transforme en DTO qu’on peut renvoyer au client.
    Classes toClasses(ClassesEntity classesEntity);
    //prend un DTO (reçu d’une requête API) et le transforme en entité prête à être sauvegardée en base.
    ClassesEntity fromClasses(Classes classes);

    default long map(SectorsEntity sector) {
        return (sector != null) ? sector.getId() : 0L;
    }

    default SectorsEntity map(long id) {
        if (id == 0) return null; // si pas de secteur fourni
        SectorsEntity sector = new SectorsEntity();
        sector.setId(id);
        return sector;
    }
}
