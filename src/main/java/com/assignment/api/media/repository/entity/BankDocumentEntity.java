package com.assignment.api.media.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(value = "bank_documents")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankDocumentEntity {

    @Id
    String id;

    String name;
}
