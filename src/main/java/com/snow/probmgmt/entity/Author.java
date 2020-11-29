package com.snow.probmgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String korName;

    @Column(nullable = false)
    private String engName;
    
    protected Author() {}

    public Author(String korName) {
        this.korName = korName;
    }

    public Long getId() {
        return id;
    }

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    @Override
    public String toString() {
        return String.format("[%d, %s, %s]", id, korName, engName);
    }
}
