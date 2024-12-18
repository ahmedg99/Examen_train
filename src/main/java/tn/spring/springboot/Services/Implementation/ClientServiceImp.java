package tn.spring.springboot.Services.Implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IClientService;
import tn.spring.springboot.entities.Client;
import tn.spring.springboot.repositories.ClientRepository;

import java.util.List;

@Service
@Slf4j
public class ClientServiceImp implements IClientService {

    @Autowired
    ClientRepository clientRepository;


    @Override
    public Long AjouterClient(Client C) {
        clientRepository.save(C);
        log.info("Le Client" + C.getNomClient() + " est ajouté avec succès");
        return C.getIdClient();
    }

    @Override
    public List<Client> afficherClients() {
        return clientRepository.findAll();
    }
}
