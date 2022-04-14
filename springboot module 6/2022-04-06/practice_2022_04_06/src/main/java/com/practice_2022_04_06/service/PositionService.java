package com.practice_2022_04_06.service;

import com.practice_2022_04_06.entity.Position;
import java.util.List;

public interface PositionService {
    Position findById(Long id);

    Position save(Position obj);

    boolean deleteById(Long id);

    List<Position> findAll(int pos, int size, String by, String direction);

    Long countAll();
}
