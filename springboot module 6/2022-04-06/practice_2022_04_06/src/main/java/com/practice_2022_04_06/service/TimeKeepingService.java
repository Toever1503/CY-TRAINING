package com.practice_2022_04_06.service;

import com.practice_2022_04_06.entity.Timekeeping;

import java.util.List;

public interface TimeKeepingService {
    Timekeeping findById(Long id);

    Timekeeping save(Timekeeping obj);

    boolean deleteById(Long id);

    List<Timekeeping> findAll(int pos, int size, String by, String direction);

    long countAll();
}
