package com.repository.impl;

import com.domain.People;
import com.domain.Product;
import com.repository.PeopleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository {

    private List<People> List;

    public PeopleRepositoryImpl() {
        List = new ArrayList<People>();
        List.add(new People(1l, "people 1", 10));
        List.add(new People(11l, "people 11", 11));
        List.add(new People(12l, "people 12", 12));
        List.add(new People(13l, "people 13", 13));
        List.add(new People(14l, "people 14", 14));
        List.add(new People(15l, "people 15", 15));
        List.add(new People(16l, "people 16", 16));
        List.add(new People(17l, "people 17", 17));
        List.add(new People(18l, "people 18", 18));
        List.add(new People(19l, "people 19", 19));
        List.add(new People(20l, "people 20", 20));
        List.add(new People(21l, "people 21", 21));
        List.add(new People(22l, "people 22", 22));
        List.add(new People(23l, "people 23", 23));
        List.add(new People(24l, "people 24", 24));
        List.add(new People(25l, "people 25", 25));
        List.add(new People(26l, "people 26", 26));
        List.add(new People(27l, "people 27", 27));

    }

    @Override
    public People add(People o) {
        if (List.contains(o))
            return null;
        else List.add(o);
        return o;
    }

    @Override
    public People update(People o) {
        int index = List.indexOf(o);
        if (index == -1)
            return null;
        List.set(index, o);
        return o;
    }

    @Override
    public People findById(Long id) {
        People p = null;
        for (int i = 0; i < List.size(); ++i) {
            if (id.equals(List.get(i).getId())) {
                p = List.get(i);
                break;
            }
        }
        return p;
    }

    @Override
    public List<People> findAll() {
        return List;
    }

    @Override
    public boolean deleteById(Long id) {
        People p = new People();
        p.setId(id);
        List.remove(p);
        return true;
    }

    @Override
    public List<People> findAll(String sortBy) {
        System.out.println("sort by-> " + sortBy);
        List<People> ls = new ArrayList<People>(List);
        ls.sort((a, b) -> {
            if (sortBy.equalsIgnoreCase("desc"))
                return -(a.getAge() - b.getAge());
            return a.getAge() - b.getAge();
        });
        return ls;
    }
}
