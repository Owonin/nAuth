package com.example.netflix.nAuth.services;

import com.example.netflix.nAuth.data.entities.ClientEntity;
import com.example.netflix.nAuth.data.repo.ClientRepository;
import com.example.netflix.nAuth.exceptions.LoginException;
import com.example.netflix.nAuth.exceptions.RegistatoinException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public void register(String clientId, String clientSecret) {
        if(repository.findById(clientId).isPresent())
            throw new RegistatoinException("Client with id:" + clientId + "already registered");
        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        repository.save(new ClientEntity(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) {
        Optional<ClientEntity> optionalUserEntity = repository
                .findById(clientId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with id: " + clientId + " not found");

        ClientEntity clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}
