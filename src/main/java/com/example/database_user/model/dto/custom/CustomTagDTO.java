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
@AllArgsConstructor
public class CustomTagDTO {
  @Builder.Default
  private String id = UUID.randomUUID().toString();
  private String typeOfData;
  private String data;
  private String tagName;
}
