package com.example.database_user.repositories.entity;

import java.util.UUID;
import javax.annotation.processing.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Builder
@Data
@AllArgsConstructor
public class CustomTagEntity {
  @Id
  @Builder.Default
  private String id = UUID.randomUUID().toString();
  private String typeOfData;
  private String data;
  private String tagName;
}
