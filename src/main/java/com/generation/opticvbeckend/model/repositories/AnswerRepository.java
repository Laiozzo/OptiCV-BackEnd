package com.generation.opticvbeckend.model.repositories;

import com.generation.opticvbeckend.model.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
