package com.example.netflix.nAuth.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    private String id;
    private String hash;
}
