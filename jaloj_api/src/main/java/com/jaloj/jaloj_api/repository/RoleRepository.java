package com.jaloj.jaloj_api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jaloj.jaloj_api.models.ERole;
import com.jaloj.jaloj_api.models.Role;


public interface RoleRepository extends MongoRepository<Role, String>{

    Optional<Role> findByName(ERole name);
}
