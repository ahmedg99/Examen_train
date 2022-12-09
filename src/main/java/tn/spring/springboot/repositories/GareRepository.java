package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.springboot.entities.Gare;

public interface GareRepository extends JpaRepository<Gare,Long> {
    Gare findByNom(String nom ) ;
}
