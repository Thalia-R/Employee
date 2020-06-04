package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class FileRepository implements CrudRepository<Files, Integer> {


    @Override
    public <S extends Files> S save(S s) {
        return null;
    }

    @Override
    public <S extends Files> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Files> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Files> findAll() {
        return null;
    }

    @Override
    public Iterable<Files> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Files files) {

    }

    @Override
    public void deleteAll(Iterable<? extends Files> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
