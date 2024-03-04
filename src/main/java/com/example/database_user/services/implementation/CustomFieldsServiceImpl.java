package com.example.database_user.services.implementation;

import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.dto.custom.CustomTagDTO;
import com.example.database_user.model.mapper.CustomFieldsMapper;
import com.example.database_user.repositories.CustomFieldsRepository;
import com.example.database_user.services.CustomFieldsService;
import com.example.database_user.services.exception.ListIsEmptyException;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Data
public class CustomFieldsServiceImpl implements CustomFieldsService {

  @Autowired
  private CustomFieldsRepository customFieldsRepository;
  @Autowired
  private CustomFieldsMapper customFieldsMapper;

  @Override
  public CustomFieldsDTO createCustomField(CustomFieldsDTO customFieldsDTO) {
    return customFieldsMapper.toDTO(
        customFieldsRepository.save(customFieldsMapper.toEntity(customFieldsDTO)));
  }

  @Override
  public void deleteCustomField(String fieldId) {
    customFieldsRepository.deleteById(fieldId);
  }

  @Override
  public List<CustomFieldsDTO> getAllCustomFields() {
    List<CustomFieldsDTO> customFieldsDTOS = customFieldsRepository.findAll().stream()
        .map(customFieldsMapper::toDTO).toList();
    if (customFieldsDTOS.isEmpty()) {
      throw new ListIsEmptyException();
    } else {
      return customFieldsDTOS;
    }

  }

  @Override
  public CustomFieldsDTO getCustomFieldById(String fieldId) {
    CustomFieldsDTO customFieldsDTO = customFieldsMapper.toDTO(
        customFieldsRepository.getCustomFieldsEntitiesById(fieldId));
    if (customFieldsDTO == null) {
      throw new ListIsEmptyException();
    } else {
      return customFieldsDTO;
    }
  }

  @Override
  public void updateCustomField(String fieldId, String fieldName) {
    CustomFieldsDTO customFieldsDTO = customFieldsMapper.toDTO(
        customFieldsRepository.getCustomFieldsEntitiesById(fieldId));
    customFieldsDTO.setName(fieldName);
    customFieldsRepository.save(customFieldsMapper.toEntity(customFieldsDTO));
  }

  @Override
  public void addTagToCustomField(String fieldId, CustomTagDTO tagDTO) {
    CustomFieldsDTO customFieldsDTO = getCustomFieldById(fieldId);
    customFieldsDTO.getCustomTagDTOS().add(tagDTO);
    customFieldsRepository.save(customFieldsMapper.toEntity(customFieldsDTO));
  }

  @Override
  public void deleteTagFromCustomField(String tagId, String fieldId) {
    CustomFieldsDTO customFieldsDTO = getCustomFieldById(fieldId);
    if (customFieldsDTO.getCustomTagDTOS()
        .removeIf(customTagDTO -> customTagDTO.getId().equals(tagId))) {
      customFieldsRepository.save(customFieldsMapper.toEntity(customFieldsDTO));
    }
  }
}
