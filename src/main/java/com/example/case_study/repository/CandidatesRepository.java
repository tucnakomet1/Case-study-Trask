package com.example.case_study.repository;

import com.example.case_study.entity.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatesRepository extends JpaRepository<Candidates, Long> {
    List<Candidates> findByCandidateContaining(String candidate);
}
