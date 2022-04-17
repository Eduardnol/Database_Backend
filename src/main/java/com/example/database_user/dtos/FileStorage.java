package com.example.database_user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileStorage {
	@JsonProperty("name")
	private String name;
	@JsonProperty("url")
	private String url;

}
