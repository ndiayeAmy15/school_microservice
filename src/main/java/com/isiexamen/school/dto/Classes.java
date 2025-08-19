package com.isiexamen.school.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Classes {
    private String className;
    private String description;

    private long sector;

    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }

    public long getSector() {
        return sector;
    }

    public void setSector(long sector) {
        this.sector = sector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
