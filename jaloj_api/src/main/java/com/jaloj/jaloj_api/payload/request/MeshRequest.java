package com.jaloj.jaloj_api.payload.request;

import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

public class MeshRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private MultipartFile file;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
