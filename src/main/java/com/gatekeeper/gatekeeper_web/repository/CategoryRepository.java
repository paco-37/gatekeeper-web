package com.gatekeeper.gatekeeper_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gatekeeper.gatekeeper_web.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}