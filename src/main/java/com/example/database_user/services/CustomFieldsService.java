package com.example.database_user.services;

import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CustomFieldsService {

  CustomFieldsDTO createCustomTag(CustomFieldsDTO customFieldsDTO);

  void deleteCustomTag(String tagId);

  List<CustomFieldsDTO> getAllCustomTags();

  CustomFieldsDTO getCustomTagById(String tagId);

  void updateCustomTag(String tagId, String tagName);

}
