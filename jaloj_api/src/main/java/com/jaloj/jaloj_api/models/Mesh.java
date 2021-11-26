package com.jaloj.jaloj_api.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection =  "meshes")
public class Mesh {
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String description;

    private Binary file;

    @DBRef
    private Set<MeshCategory> meshCategories = new HashSet<>();

    public Mesh(){

    }

    public Mesh(String name, String description, Binary file) {
        this.name = name;
        this.description = description;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Binary getFile() {
        return file;
    }

    public void setFile(Binary file) {
        this.file = file;
    }

    public Set<MeshCategory> getMeshCategories() {
        return meshCategories;
    }

    public void setMeshCategories(Set<MeshCategory> meshCategories) {
        this.meshCategories = meshCategories;
    }

    @Override
    public String toString() {
        return "Mesh{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", meshCategories=" + meshCategories +
                '}';
    }
}
