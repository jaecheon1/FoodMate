package com.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.SatisfactionSurvey;

public interface SatisfactionRepository extends JpaRepository<SatisfactionSurvey, Long> {
}