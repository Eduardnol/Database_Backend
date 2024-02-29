package com.example.database_user.model.dto.custom;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CustomFieldsDTO {

  private String id;
  private String name;
  private List<CustomTagDTO> customTagDTOS;

}
