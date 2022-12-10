package tn.spring.springboot.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.springboot.entities.Gare;
import tn.spring.springboot.entities.Train;
import tn.spring.springboot.entities.Ville;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
public interface TrainRepository extends JpaRepository<Train , Long > {


    @Query("SELECT T FROM Train T WHERE T.GareDepart.nom ='EZZAHRA LYCEE' and T.heureDepart=7.45  and T.GareArrivee.nom ='RADES LYCEE' ")
    Train findByGareDepartandAndGareArriveeAndHeureArrivee();

    @Query("SELECT AVG(T.nbPlaceLibre) FROM Train T WHERE T.GareDepart.localisation =:localisation")
    int AvgNbPlacesLibre(@Param("localisation") Ville localisation) ;

    @Query("SELECT T FROM Train T WHERE T.GareArrivee.nom =:nom")
    List<Train> trainindirectes(@Param("nom") String nom ) ;

    @Query("SELECT T FROM Train T WHERE T.GareDepart.nom =:nom and T.heureDepart=:heureDepart")
    Train findTrainByDateArriveeAndgAndGareDepart(@Param("nom") String nom , @Param("heureDepart") Double heureDepart );


    @Query("SELECT T FROM Train T WHERE T.dateArrivee <current_date ")
    List<Train> findTrainByDateArriveeBeforeee();
}


