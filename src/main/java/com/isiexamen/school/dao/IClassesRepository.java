package com.isiexamen.school.dao;

import com.isiexamen.school.entities.ClassesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassesRepository extends JpaRepository<ClassesEntity,Long> {
}
