package com.jaloj.jaloj_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection =  "meshes_categories")
public class MeshCategory {
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String name;

    public MeshCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
