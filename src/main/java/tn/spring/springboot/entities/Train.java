package tn.spring.springboot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@ToString
@Data
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter

    private Long idTrain;
    @Enumerated(EnumType.STRING)
    private etatTrain etat ;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateDepart ;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateArrivee ;
    private Double  heureDepart  ;
    private Double  heureArrivee  ;
    private int nbPlaceLibre ;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "train")
    @JsonIgnore
    @ToString.Exclude
     private List<Client> clients ;

    @ManyToOne
    private Gare GareDepart ;




    @ManyToOne
     private Gare GareArrivee ;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return nbPlaceLibre == train.nbPlaceLibre && Objects.equals(idTrain, train.idTrain) && etat == train.etat && Objects.equals(dateDepart, train.dateDepart) && Objects.equals(dateArrivee, train.dateArrivee) && Objects.equals(heureDepart, train.heureDepart) && Objects.equals(heureArrivee, train.heureArrivee) && Objects.equals(clients, train.clients) && Objects.equals(GareDepart, train.GareDepart) && Objects.equals(GareArrivee, train.GareArrivee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrain, etat, dateDepart, dateArrivee, heureDepart, heureArrivee, nbPlaceLibre, clients, GareDepart, GareArrivee);
    }
}