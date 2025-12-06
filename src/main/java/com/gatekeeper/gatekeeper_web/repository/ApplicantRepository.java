package com.gatekeeper.gatekeeper_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gatekeeper.gatekeeper_web.model.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    List<Applicant> findTop10ByOrderByTotalScoreDesc();
}