package com.generation.opticvbeckend.model.repositories;

import com.generation.opticvbeckend.model.entities.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypeRepository extends JpaRepository<JobType, Long> {
}
