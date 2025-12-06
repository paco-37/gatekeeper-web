package com.gatekeeper.gatekeeper_web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {}

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}