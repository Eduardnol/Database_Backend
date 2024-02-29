package com.example.database_user.repositories;

import com.example.database_user.repositories.entity.CustomFieldsEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFieldsRepository extends MongoRepository<CustomFieldsEntity, String> {

  CustomFieldsEntity getCustomFieldsEntitiesById(String name);

  void deleteById(String id);


}
