package com.example.database_user.repositories.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Builder
@Data
@AllArgsConstructor
public class CustomTagEntity {
  @Id
  private String id;
  private String typeOfData;
  private String data;
  private String tagName;
}
