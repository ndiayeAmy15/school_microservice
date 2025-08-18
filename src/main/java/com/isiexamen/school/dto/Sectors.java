package com.isiexamen.school.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sectors {

   // @NotNull(message = "Le nom ne doit pas etre null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
