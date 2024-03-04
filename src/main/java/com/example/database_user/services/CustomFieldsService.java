package com.example.database_user.services;

import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CustomFieldsService {

  CustomFieldsDTO createCustomField(CustomFieldsDTO customFieldsDTO);

  void deleteCustomField(String tagId);

  List<CustomFieldsDTO> getAllCustomFields();

  CustomFieldsDTO getCustomFieldById(String tagId);

  void updateCustomField(String fieldId, String fieldName);

  void addTagToCustomField(String fieldId, CustomTagDTO tagDTO);

  void deleteTagFromCustomField(String tagId, String fieldId);


}
