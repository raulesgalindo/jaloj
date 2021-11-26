package com.jaloj.jaloj_api.repository;

import com.jaloj.jaloj_api.models.Mesh;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MeshRepository extends MongoRepository<Mesh, String>{



}
