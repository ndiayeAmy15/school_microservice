package com.isiexamen.school.services;

import com.isiexamen.school.dao.ISectorsRepository;
import com.isiexamen.school.dto.Sectors;
import com.isiexamen.school.entities.SectorsEntity;
import com.isiexamen.school.exception.RequestException;
import com.isiexamen.school.mapping.SectorsMapper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "sectors")
public class SectorsService {

    private final ISectorsRepository iSectorsRepository;
    private final SectorsMapper sectorsMapper;
    private final MessageSource messageSource;

    // Injection par constructeur
    public SectorsService(ISectorsRepository iSectorsRepository, SectorsMapper sectorsMapper, MessageSource messageSource) {
        this.iSectorsRepository = iSectorsRepository;
        this.sectorsMapper = sectorsMapper;
        this.messageSource = messageSource;
    }

    // Récupérer tous les secteurs avec cache
    @Cacheable
    @Transactional(readOnly = true)
    public List<Sectors> getSectors() {
        return iSectorsRepository.findAll()
                .stream()
                .map(sectorsMapper::toSectors)
                .collect(Collectors.toList());
    }

    // Récupérer un secteur par ID avec cache
    @Cacheable(key = "#id")
    @Transactional(readOnly = true)
    public Sectors getSector(int id) {
        SectorsEntity entity = iSectorsRepository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("sector.notfound", new Object[]{id}, Locale.getDefault())
                ));
        return sectorsMapper.toSectors(entity);
    }

    // Créer un nouveau secteur
    @Transactional
    public Sectors createSector(Sectors sector) {
        SectorsEntity entity = sectorsMapper.fromSectors(sector);
        SectorsEntity saved = iSectorsRepository.save(entity);
        return sectorsMapper.toSectors(saved);
    }

    // Mettre à jour un secteur existant
    @CacheEvict(key = "#id")
    @Transactional
    public Sectors updateSector(int id, Sectors sector) {
        SectorsEntity existing = iSectorsRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException(
                        messageSource.getMessage("sector.notfound", new Object[]{id}, Locale.getDefault())
                ));
        existing.setName(sector.getName());
        // Si le secteur a d'autres propriétés, les mettre à jour ici

        SectorsEntity updated = iSectorsRepository.save(existing);
        return sectorsMapper.toSectors(updated);
    }

    // Supprimer un secteur
    @CacheEvict(key = "#id")
    @Transactional
    public void deleteSector(int id) {
        try {
            iSectorsRepository.deleteById((long) id);
        } catch (Exception e) {
            throw new RequestException(
                    messageSource.getMessage("sector.errordeletion", new Object[]{id}, Locale.getDefault()),
                    HttpStatus.CONFLICT
            );
        }
    }
}
