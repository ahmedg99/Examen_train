package tn.spring.springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.springboot.entities.Etudiant;

import java.util.List;

@Repository
public interface EtudiantRepository  extends JpaRepository<Etudiant , Long > {
    Etudiant  findAllByIdEtudiant(Long id) ;
 }
