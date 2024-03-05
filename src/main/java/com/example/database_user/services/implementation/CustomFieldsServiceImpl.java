package com.example.database_user.services.implementation;

import com.example.database_user.model.dto.custom.CustomFieldsDTO;
import com.example.database_user.model.mapper.CustomFieldsMapper;
import com.example.database_user.repositories.CustomFieldsRepository;
import com.example.database_user.services.CustomFieldsService;
import com.example.database_user.services.exception.ListIsEmptyException;
import java.util.List;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Data
@Log4j2
public class CustomFieldsServiceImpl implements CustomFieldsService {

  @Autowired
  private CustomFieldsRepository customFieldsRepository;
  @Autowired
  private CustomFieldsMapper customFieldsMapper;

  @Override
  public CustomFieldsDTO createCustomTag(CustomFieldsDTO customFieldsDTO) {
    return customFieldsMapper.toDTO(
        customFieldsRepository.save(customFieldsMapper.toEntity(customFieldsDTO)));
  }

  @Override
  public void deleteCustomTag(String tagId) {
    customFieldsRepository.deleteById(tagId);
  }

  @Override
  public List<CustomFieldsDTO> getAllCustomTags() {
    List<CustomFieldsDTO> customFieldsDTOS = customFieldsRepository.findAll().stream()
        .map(customFieldsMapper::toDTO).toList();
    if (customFieldsDTOS.isEmpty()) {
      throw new ListIsEmptyException();
    } else {
      return customFieldsDTOS;
    }

  }

  @Override
  public CustomFieldsDTO getCustomTagById(String tagId) {
    CustomFieldsDTO customFieldsDTO = customFieldsMapper.toDTO(
        customFieldsRepository.getCustomFieldsEntitiesById(tagId));
    if (customFieldsDTO == null) {
      throw new ListIsEmptyException();
    } else {
      return customFieldsDTO;
    }
  }

  @Override
  public void updateCustomTag(String tagId, String tagName) {

  }
}
