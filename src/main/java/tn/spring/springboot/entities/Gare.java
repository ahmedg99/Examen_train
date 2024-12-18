package tn.spring.springboot.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Gare implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idGare;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Ville localisation;


}