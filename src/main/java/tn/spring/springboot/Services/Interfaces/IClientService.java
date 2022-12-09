package tn.spring.springboot.Services.Interfaces;

import tn.spring.springboot.entities.Client;
import tn.spring.springboot.entities.Etudiant;

import java.util.List;

public interface IClientService {
    public Long AjouterClient(Client C) ;
    List<Client> afficherClients() ;

}
