package com.generation.opticvbeckend.model.repositories;

import com.generation.opticvbeckend.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByUsername(String username);
}
