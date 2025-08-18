package com.isiexamen.school.controller;

import com.isiexamen.school.dto.Classes;
import com.isiexamen.school.services.ClassesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/classes")

public class ClassesController {
    //l’injection via constructeur) pour que Spring injecte automatiquement le service.
    @Autowired
    private ClassesService classesService;

    @GetMapping
    public List<Classes> getClasses(){
        return classesService.getClasses();
    }

    //@PathVariable("id") sert à récupérer la valeur du paramètre id directement depuis l’URL.
    @GetMapping("/{id}")
    public Classes getclasse(@PathVariable("id") int id){
        return classesService.getClasse(id);
    }

    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public Classes createClasse(@Valid @RequestBody Classes classes){
        return classesService.createClasse(classes);
    }

    @PutMapping("/{id}")
    public Classes updateClasse(@PathVariable("id") int id ,@Valid @RequestBody Classes classes){
        return classesService.updateClasse(id,classes);
    }

    @DeleteMapping("/{id}")
    public void deleteClasses(@PathVariable("id") int id){
        classesService.deleteClasse(id);
    }
}
