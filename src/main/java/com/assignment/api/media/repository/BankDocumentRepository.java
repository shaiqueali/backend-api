package com.assignment.api.media.repository;

import com.assignment.api.media.repository.entity.BankDocumentEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BankDocumentRepository extends ReactiveMongoRepository<BankDocumentEntity, String> {
}
