package com.example.database_user.model.dto.Custom;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * CustomTagDTO fields for the person class, you can personalize with selected, name and tagname
 */
@Data
@AllArgsConstructor
public class CustomTagDTO {

  private String id;
  private String typeOfData;
  private String data;
  private String tagName;


  public CustomTagDTO() {
    typeOfData = "";
    data = "";
    tagName = "";
  }
}
