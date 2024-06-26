package com.example.wordlyweek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.wordlyweek.model.*;
import com.example.wordlyweek.repository.*;
import java.util.*;

@Service
public class MagazineJpaService implements MagazineRepository {
    @Autowired
    private MagazineJpaRepository mjr;

    @Autowired
    private WriterJpaRepository wjr;

    public ArrayList<Magazine> getMagazine() {
        List<Magazine> mag = mjr.findAll();
        ArrayList<Magazine> all = new ArrayList<>(mag);
        return all;
    }

    public Magazine getMagazineById(int magazineId) {
        try {
            Magazine magazine = mjr.findById(magazineId).get();
            return magazine;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Magazine addMagazine(Magazine magazine) {
        List<Integer> writerIds = new ArrayList<>();
        for (Writer write : magazine.getWriters()) {
            writerIds.add(write.getWriterId());
        }
        List<Writer> writers = wjr.findAllById(writerIds);
        magazine.setWriters(writers);

        for (Writer write : writers) {
            write.getMagazine().add(magazine);
        }
        Magazine mag = mjr.save(magazine);
        wjr.saveAll(writers);
        return mag;
    }

    public Magazine updateMagazine(int magazineId, Magazine magazine) {
        try {
            Magazine newMagazine = mjr.findById(magazineId).get();

            if (magazine.getTitle() != null) {
                newMagazine.setTitle(magazine.getTitle());
            }
            if (magazine.getPublicationDate() != null) {
                newMagazine.setPublicationDate(magazine.getPublicationDate());
            }
            if (magazine.getWriters() != null) {
                List<Writer> writers = newMagazine.getWriters();
                for (Writer writer : writers) {
                    writer.getMagazine().remove(newMagazine);
                }
                wjr.saveAll(writers);
                List<Integer> newWriterIds = new ArrayList<>();
                for (Writer writer : magazine.getWriters()) {
                    newWriterIds.add(writer.getWriterId());
                }
                List<Writer> newWriter = wjr.findAllById(newWriterIds);
                for (Writer writer : newWriter) {
                    writer.getMagazine().add(newMagazine);
                }
                wjr.saveAll(newWriter);
                newMagazine.setWriters(newWriter);
            }
            return mjr.save(newMagazine);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteMagazine(int magazineId) {
        try {
            Magazine magazine = mjr.findById(magazineId).get();
            List<Writer> writer = magazine.getWriters();
            for (Writer write : writer) {
                write.getMagazine().remove(magazine);
            }
            wjr.saveAll(writer);
            mjr.deleteById(magazineId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Writer> getMagazineWriter(int magazineId) {
        try {
            Magazine magazine = mjr.findById(magazineId).get();
            return magazine.getWriters();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
