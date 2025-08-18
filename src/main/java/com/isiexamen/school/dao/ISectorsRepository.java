package com.isiexamen.school.dao;

import com.isiexamen.school.entities.SectorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISectorsRepository extends JpaRepository<SectorsEntity,Long> {
}
