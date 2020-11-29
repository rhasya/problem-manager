package com.snow.probmgmt.repository;

import java.util.List;

import com.snow.probmgmt.entity.Author;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByKorName(String korName);
    Author findById(long id);
}