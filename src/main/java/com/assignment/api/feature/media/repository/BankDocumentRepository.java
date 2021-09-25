package com.assignment.api.feature.media.repository;

import com.assignment.api.feature.media.repository.entity.BankDocumentEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BankDocumentRepository extends ReactiveMongoRepository<BankDocumentEntity, String> {
}
