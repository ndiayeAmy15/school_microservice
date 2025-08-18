package com.isiexamen.school.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sectors")
public class SectorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 100)
    private String name;
    @OneToMany(mappedBy = "sector")
    private List<ClassesEntity> classes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassesEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassesEntity> classes) {
        this.classes = classes;
    }
}
