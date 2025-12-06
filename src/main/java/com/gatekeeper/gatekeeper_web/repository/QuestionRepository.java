package com.gatekeeper.gatekeeper_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gatekeeper.gatekeeper_web.model.Question;

@Repository 
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
