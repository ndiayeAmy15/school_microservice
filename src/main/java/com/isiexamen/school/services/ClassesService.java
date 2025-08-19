package com.isiexamen.school.services;

import com.isiexamen.school.dao.ISectorsRepository;
import com.isiexamen.school.dto.Classes;
import com.isiexamen.school.entities.ClassesEntity;
import com.isiexamen.school.entities.SectorsEntity;
import com.isiexamen.school.exception.RequestException;
import com.isiexamen.school.mapping.ClassesMapper;
import com.isiexamen.school.dao.IClassesRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;


@Service
//our configurer le comportement du cache au niveau d’une classe
@CacheConfig(cacheNames = "classes")

public class ClassesService {
    private IClassesRepository iClassesRepository;
    private ISectorsRepository iSectorsRepository;
    private ClassesMapper classesMapper;

    //Sert à récupérer des messages localisés à partir de fichiers messages.properties
    MessageSource messageSource;

    //injecte les dépendances via l’injection par constructeur
    public ClassesService(IClassesRepository iClassesRepository,ISectorsRepository iSectorsRepository,ClassesMapper classesMapper,MessageSource messageSource){
        this.iClassesRepository=iClassesRepository;
        this.classesMapper=classesMapper;
        this.messageSource=messageSource;
        this.iSectorsRepository=iSectorsRepository;
    }

    //Met en cache le résultat d’une méthode pour éviter des appels répétés
    @Cacheable
    @Transactional(readOnly = true)
    public List<Classes> getClasses() {
        return iClassesRepository.findAll()
                .stream()                      // transforme List<ClassesEntity> en Stream
                .map(classesMapper::toClasses) // convertit chaque entity en DTO
                .collect(Collectors.toList()); // collecte dans une List<Classes>
    }

    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    public Classes getClasse(int id){
        return  classesMapper.toClasses(iClassesRepository.findById((long) id).
                orElseThrow(()->
                new EntityNotFoundException(messageSource.getMessage("classe.notfound",new Object[]{id}, Locale.getDefault()))
        ));
    }

    @Transactional
    public Classes createClasse(Classes classe){
        SectorsEntity sector = iSectorsRepository.findById(classe.getSector())
                .orElseThrow(() -> new RuntimeException("Sector not found"));
        ClassesEntity entity = new ClassesEntity();
        entity.setClassName(classe.getClassName());
        entity.setDescription(classe.getDescription());
        entity.setSector(sector);
        return classesMapper.toClasses(iClassesRepository.save(classesMapper.fromClasses(classe)));

    }

    @Cacheable(key = "#id")
    @Transactional
    public Classes updateClasse(int id,Classes classes){
        ClassesEntity existingClasseEntity = iClassesRepository.findById((long) id)
            .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        SectorsEntity sector = iSectorsRepository.findById(classes.getSector())
                .orElseThrow(() -> new RuntimeException("Sector not found"));
        existingClasseEntity.setClassName(classes.getClassName());
        existingClasseEntity.setDescription(classes.getDescription());
        existingClasseEntity.setSector(sector);

        ClassesEntity classesEntityUpdate=iClassesRepository.save(existingClasseEntity);
        return classesMapper.toClasses(classesEntityUpdate);
    }

    @CacheEvict(key="#id")
    @Transactional
    public void deleteClasse(int id){
        try {
            iClassesRepository.deleteById((long) id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("role.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }




}
