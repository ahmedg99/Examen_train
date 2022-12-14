package tn.spring.springboot.Services.Implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.ITrainService;
import tn.spring.springboot.entities.Client;
import tn.spring.springboot.entities.Gare;
import tn.spring.springboot.entities.Train;
import tn.spring.springboot.entities.etatTrain;
import tn.spring.springboot.repositories.ClientRepository;
import tn.spring.springboot.repositories.GareRepository;
import tn.spring.springboot.repositories.TrainRepository;

import java.util.List;

@Service
@Slf4j
public class TrainService implements ITrainService {


    @Autowired
    TrainRepository trainRepository ;
    @Autowired
    GareRepository gareRepository ;
    @Autowired
    ClientRepository clientRepository ;


    @Override
    public Train ajouterTrain(Train train) {
        return trainRepository.save(train) ;
    }

    @Override
    public List<Train> listTrain() {
        return trainRepository.findAll();
    }

    @Override
    public void affecterTrainAGare(Long idTrain, Long idGareDepart, Long idGareArrivee) {
        Train train = trainRepository.findById(idTrain).get() ;
        Gare gareDepart = gareRepository.findById(idGareDepart).get();
        Gare gareArrivee= gareRepository.findById(idGareArrivee).get();
        train.setGareArrivee(gareArrivee);
        train.setGareDepart(gareDepart);
        trainRepository.save(train);
        log.info("les gares " + gareArrivee.getNom()  +"et"+ gareDepart.getNom()   + "sont affectée aux train :"  + train.getIdTrain()  );
    }

    @Override
    public void affecterTainAClient(Long idClient, Long idGareDepart) {
         Train train = trainRepository.findByGareDepartandAndGareArriveeAndHeureArrivee(idGareDepart);
        Client client = clientRepository.findById(idClient).get() ;
        //System.out.println(train);

        if(train.getNbPlaceLibre()>0) {
            client.setTrain(train);
            clientRepository.save(client);
            train.setNbPlaceLibre((train.getNbPlaceLibre()-1));
            log.info("client ajoutée au train ");
            trainRepository.save(train);
        }
        else {
            log.info("places non disponibles ");
            }


    }

    @Override
    public int TrainPlacesLibres(Long idGareDepart) {
        Gare gare = gareRepository.findById(idGareDepart).get();
        return trainRepository.AvgNbPlacesLibre(gare.getLocalisation());
    }

    @Override
    public List<Train> ListerTrainsIndirects(Long idGareDepart, Long idGareArrivee) {
/*
        Gare garedepart = gareRepository.findById(idGareDepart).get();
        Gare garearrivee= gareRepository.findById(idGareArrivee).get();
        log.info((trainRepository.trainindirectes(garearrivee.getNom()).toString()));
        List<Train> listetrain = trainRepository.trainindirectes(garearrivee.getNom()) ;
        return listetrain.stream().filter(e->!(e.getGareDepart().getNom().equals(garedepart.getNom()))).collect(Collectors.toList());
        */

        return trainRepository.findAllByGareDepartIdGareNotAndGareArriveeIdGare(idGareDepart,idGareArrivee);
    }

    @Override
    public void DesaffecterClientsTrain(Long idGareDepart, double heureDepart) {
        Gare gare = gareRepository.findById(idGareDepart).get();
         Train train = trainRepository.findTrainByDateArriveeAndgAndGareDepart(gare.getNom(),heureDepart) ;
        // train.setNbPlaceLibre((int)train.getClients().stream().count());
        for (int i=0 ; i< train.getClients().size();i++) {
            train.getClients().get(i).setTrain(null);
            clientRepository.save(train.getClients().get(i)) ;

        }
        train.getClients().clear();
        train.setEtat(etatTrain.valueOf("PREVU"));
        trainRepository.save(train);
    }



   // @Scheduled(fixedRate = 2000)
    @Override
    public void TrainsEnGare()  {
        List<Train> trains = trainRepository.findTrainByDateArriveeBeforeee() ;
        for(int i=0 ; i< trains.size();i++) {
            log.info(String.valueOf(trains.get(i).toString()));
        }

    }


}
