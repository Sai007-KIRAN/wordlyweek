package com.example.wordlyweek.repository;

import java.util.*;
import com.example.wordlyweek.model.*;

public interface MagazineRepository {
    ArrayList<Magazine> getMagazine();

    Magazine getMagazineById(int magazineId);

    Magazine addMagazine(Magazine magazine);

    Magazine updateMagazine(int magazineId, Magazine magazine);

    void deleteMagazine(int magazineId);

    List<Writer> getMagazineWriter(int magazineId);
}