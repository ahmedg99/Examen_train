package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.springboot.entities.Client;

public interface ClientRepository   extends JpaRepository<Client , Long >{

}
