package com.snow.probmgmt.controller;

import java.util.List;

import com.snow.probmgmt.entity.Author;
import com.snow.probmgmt.repository.AuthorRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app")
public class AuthorController {

    private AuthorRepository repository;

    public AuthorController(AuthorRepository authorRepository) {
        this.repository = authorRepository;
    }

    @GetMapping("/authors") 
    public Iterable<Author> authors() {
        return repository.findAll();
    }

    @GetMapping("/author")
    public Author author(@RequestParam(value="korName", defaultValue="선호") String korName) {
        List<Author> result = repository.findByKorName(korName);
        if (result.size() == 1) {
            return result.get(0);
        }
        return null;
    }
}