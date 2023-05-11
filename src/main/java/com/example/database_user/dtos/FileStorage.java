package com.example.database_user.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileStorage {

    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;

}
