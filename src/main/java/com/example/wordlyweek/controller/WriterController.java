package com.example.wordlyweek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.wordlyweek.model.*;
import com.example.wordlyweek.service.*;
import java.util.*;

// Write your code here

@RestController
public class WriterController {
    @Autowired
    private WriterJpaService wjs;

    @GetMapping("/magazines/writers")
    public ArrayList<Writer> getWriters() {
        return wjs.getWriters();
    }

    @GetMapping("/magazines/writers/{writerId}")
    public Writer getWriterById(@PathVariable("writerId") int writerId) {
        return wjs.getWriterById(writerId);
    }

    @PostMapping("/magazines/writers")
    public Writer addWriter(@RequestBody Writer writer) {
        return wjs.addWriter(writer);
    }

    @PutMapping("/magazines/writers/{writerId}")
    public Writer updateWriter(@PathVariable("writerId") int writerId, @RequestBody Writer writer) {
        return wjs.updateWriter(writerId, writer);
    }

    @DeleteMapping("/magazines/writers/{writerId}")
    public void deleteWriter(@PathVariable("writerId") int writerId) {
        wjs.deleteWriter(writerId);
    }

    @GetMapping("/writers/{writerId}/magazines")
    public List<Magazine> getWriterMagazine(@PathVariable("writerId") int writerId) {
        return wjs.getWriterMagazine(writerId);
    }
}