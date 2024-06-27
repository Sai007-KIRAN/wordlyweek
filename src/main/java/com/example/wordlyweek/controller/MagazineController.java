package com.example.wordlyweek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.wordlyweek.model.*;
import com.example.wordlyweek.service.*;
import java.util.*;

@RestController
public class MagazineController {

    @Autowired
    private MagazineJpaService mjs;

    @GetMapping("/magazines")
    public ArrayList<Magazine> getMagazine() {
        return mjs.getMagazine();
    }

    @GetMapping("/magazines/{magazineId}")
    public Magazine getMagazineById(@PathVariable("magazineId") int magazineId) {
        return mjs.getMagazineById(magazineId);
    }

    @PostMapping("/magazines")
    public Magazine addMagazine(@RequestBody Magazine magazine) {
        return mjs.addMagazine(magazine);
    }

    @PutMapping("/magazines/{magazineId}")
    public Magazine updateMagazine(@PathVariable("magazineId") int magazineId, @RequestBody Magazine magazine) {
        return mjs.updateMagazine(magazineId, magazine);
    }

    @DeleteMapping("/magazines/{magazineId}")
    public void deleteMagazine(@PathVariable("magazineId") int magazineId) {
        mjs.deleteMagazine(magazineId);
    }

    @GetMapping("/magazines/{magazineId}/writers")
    public List<Writer> getMagazineWriter(@PathVariable("magazineId") int magazineId) {
        return mjs.getMagazineWriter(magazineId);
    }
}