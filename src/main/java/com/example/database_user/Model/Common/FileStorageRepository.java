package com.example.database_user.Model.Common;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileStorageRepository extends MongoRepository<FileStorage, String> {
}
