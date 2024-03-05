package com.example.database_user.model.dto.custom;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * CustomTagDTO fields for the person class, you can personalize with selected, name and tagname
 */
@Builder
@Data
public class CustomTagDTO {

  private String id;
  private String typeOfData;
  private String data;
  private String tagName;

  public CustomTagDTO(String id, String typeOfData, String data, String tagName) {
    this.id = UUID.randomUUID().toString();
    this.typeOfData = typeOfData;
    this.data = data;
    this.tagName = tagName;
  }
}
