package tn.spring.springboot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long idTrain;
    @Enumerated(EnumType.STRING)
    private etatTrain etat;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateDepart;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateArrivee;
    private Double heureDepart;
    private Double heureArrivee;
    private int nbPlaceLibre;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "train")
    @JsonIgnore
    @ToString.Exclude
    private List<Client> clients;

    @ManyToOne
    private Gare gareDepart;


    @ManyToOne
    private Gare gareArrivee;


}