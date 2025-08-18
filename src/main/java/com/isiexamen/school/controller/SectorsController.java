package com.isiexamen.school.controller;

import com.isiexamen.school.dto.Sectors;
import com.isiexamen.school.dto.Sectors;

import com.isiexamen.school.services.SectorsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sectors")

public class SectorsController {


    private final SectorsService sectorsService; // Injection via constructeur

    // Injection via constructeur
    public SectorsController(SectorsService sectorsService) {
        this.sectorsService = sectorsService;
    }
    @GetMapping
    public List<Sectors> getSectors() {
        return sectorsService.getSectors();
    }

    @GetMapping("/{id}")
    public Sectors getSector(@PathVariable("id") int id) {
        return sectorsService.getSector(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sectors createSector(@RequestBody Sectors sector) {
        return sectorsService.createSector(sector);
    }

    @PutMapping("/{id}")
    public Sectors updateSector(@PathVariable("id") int id,  @RequestBody Sectors sector) {
        return sectorsService.updateSector(id, sector);
    }


    @DeleteMapping("/{id}")
    public void deleteSector(@PathVariable("id") int id) {
        sectorsService.deleteSector(id);
    }
}
