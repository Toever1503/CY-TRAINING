package com.service.impl;

import com.domain.People;
import com.repository.PeopleRepository;
import com.service.PeopleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public People findById(Long id) {
        return peopleRepository.findById(id);
    }

    @Override
    public List<People> findAll(String sortBy) {
        return this.peopleRepository.findAll(sortBy);
    }

    @Override
    public People add(People p) {
        return this.peopleRepository.add(p);
    }

    @Override
    public People update(People p) {
        return this.peopleRepository.update(p);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.peopleRepository.deleteById(id);
    }
}
