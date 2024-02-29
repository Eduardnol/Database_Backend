package com.example.database_user.repositories.entity;

import com.example.database_user.model.dto.custom.CustomTagDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customFields")
@Builder
@Data
@AllArgsConstructor
public class CustomFieldsEntity {
  @Id
  private String id;
  private String name;
  private List<CustomTagEntity> customTagDTOS;
}
