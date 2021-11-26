package com.jaloj.jaloj_api.controllers;

import com.jaloj.jaloj_api.models.Mesh;
import com.jaloj.jaloj_api.payload.response.MessageResponse;
import com.jaloj.jaloj_api.repository.MeshRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mesh")
public class MeshController {
    @Autowired
    MeshRepository meshRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("")
    public ResponseEntity<?> addMesh(@RequestParam("name") String name,
                                     @RequestParam("description") String description,
                                     @RequestParam("file") MultipartFile file) throws IOException {

        Mesh mesh = new Mesh();
        mesh.setName(name);
        mesh.setDescription(description);
        mesh.setFile(
                new Binary(BsonBinarySubType.BINARY, file.getBytes())
        );

        mesh = meshRepository.insert(mesh);

        return ResponseEntity.ok(new MessageResponse(mesh.toString()));
    }

    @GetMapping("/{id}")
    public byte[] getMesh(@PathVariable String id) {
        Optional<Mesh> meshOptional = meshRepository.findById(id);
        Mesh mesh = meshOptional.get();
        return mesh.getFile().getData();

    }

    @GetMapping("/page/{page}/{size}")
    public Page getMeshesByPage(@PathVariable int page, @PathVariable int size) {

        Pageable pageRequest = PageRequest.of(page, size);
        Query query = new Query();
        query.fields().exclude("file");
        query.with(pageRequest);
        List list = mongoTemplate.find(query, Mesh.class);
        return PageableExecutionUtils.getPage(
                list,
                pageRequest,
                () -> mongoTemplate.count(query, Mesh.class));

    }

    @PutMapping("")
    public ResponseEntity<?> updateMesh(@RequestParam("id") String id,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("file") MultipartFile file) throws IOException{

        Optional<Mesh> meshOptional = meshRepository.findById(id);

        if(meshOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("Mesh not found") );
        }

        Mesh mesh = meshOptional.get();

        if(!name.isEmpty()){
            mesh.setName(name);
        }

        if(!description.isEmpty()){
            mesh.setDescription(description);
        }

        if(!file.isEmpty()){
            mesh.setFile(
                    new Binary(BsonBinarySubType.BINARY, file.getBytes())
            );
        }

        meshRepository.save(mesh);

        return ResponseEntity.ok(new MessageResponse(mesh.toString()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMesh(@PathVariable String id){

        Optional<Mesh> meshOptional = meshRepository.findById(id);

        if(meshOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new MessageResponse("Mesh not found") );
        }

        Mesh mesh = meshOptional.get();
        meshRepository.delete(mesh);

        return ResponseEntity.ok(new MessageResponse("Mesh deleted"));
    }
    
}
