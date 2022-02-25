package com.example.netflix.nAuth.data.repo;

import com.example.netflix.nAuth.data.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<ClientEntity, String> {

}
