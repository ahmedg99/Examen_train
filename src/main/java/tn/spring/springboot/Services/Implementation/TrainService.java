package tn.spring.springboot.Services.Implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    TrainRepository trainRepository;
    @Autowired
    GareRepository gareRepository;
    @Autowired
    ClientRepository clientRepository;


    @Override
    public Train ajouterTrain(Train train) {
        log.info("Le train  est ajouté avec succès");
        return trainRepository.save(train);
    }

    @Override
    public List<Train> listTrain() {
        return trainRepository.findAll();
    }

    @Override
    public void affecterTrainAGare(Long idTrain, Long idGareDepart, Long idGareArrivee) {
        Train train = trainRepository.findById(idTrain).orElseThrow(
                () -> new RuntimeException("Train with id : " + idTrain + "not found"));
        Gare gareDepart = gareRepository.findById(idGareDepart).orElseThrow(
                () -> new RuntimeException("Gare with id : " + idGareDepart + "not found"));
        Gare gareArrivee = gareRepository.findById(idGareArrivee).orElseThrow(
                () -> new RuntimeException("Gare with id : " + idGareArrivee + "not found"));
        train.setGareArrivee(gareArrivee);
        train.setGareDepart(gareDepart);
        trainRepository.save(train);
        log.info("les gares " + gareArrivee.getNom() + " et " + gareDepart.getNom() + "sont affectée aux train :" + train.getIdTrain());
    }

    @Override
    public void affecterTainAClient(Long idClient, Long idGareDepart) {
        Train train = trainRepository.findByGareDepartandAndGareArriveeAndHeureArrivee(idGareDepart);
        if (train == null) {
            throw new RuntimeException("Train not found for departure station: " + idGareDepart);
        }

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client with ID " + idClient + " not found"));

        if (train.getNbPlaceLibre() > 0) {
            client.setTrain(train);
            clientRepository.save(client);

            train.setNbPlaceLibre(train.getNbPlaceLibre() - 1);
            trainRepository.save(train);

            log.info("Client {} has been successfully assigned to train departing from {}.", client.getNomClient(), train.getGareDepart());
        } else {
            log.warn("No available seats on the train departing from {}.", train.getGareDepart());
        }
    }


    @Override
    public int TrainPlacesLibres(Long idGareDepart) {
        Gare gare = gareRepository.findById(idGareDepart).get();
        return trainRepository.AvgNbPlacesLibre(gare.getLocalisation());
    }

    @Override
    public List<Train> ListerTrainsIndirects(Long idGareDepart, Long idGareArrivee) {
        return trainRepository.findAllByGareDepartIdGareNotAndGareArriveeIdGare(idGareDepart, idGareArrivee);
    }

    @Override
    public void DesaffecterClientsTrain(Long idGareDepart, double heureDepart) {
        Gare gare = gareRepository.findById(idGareDepart).orElseThrow(
                () -> new RuntimeException("Gare with id : " + idGareDepart + "not found"));
        Train train = trainRepository.findTrainByDateArriveeAndgAndGareDepart(gare.getNom(), heureDepart);
        for (int i = 0; i < train.getClients().size(); i++) {
            train.getClients().get(i).setTrain(null);
            train.setNbPlaceLibre(train.getNbPlaceLibre() + 1);
            clientRepository.save(train.getClients().get(i));
        }
        train.getClients().clear();
        train.setEtat(etatTrain.valueOf("PREVU"));
        trainRepository.save(train);
    }


    @Scheduled(fixedRate = 30000)
    @Override
    public void TrainsEnGare() {
        List<Train> trains = trainRepository.findTrainByDateArriveeBeforeee();
        for (int i = 0; i < trains.size(); i++) {
            trains.get(i).setEtat(etatTrain.valueOf("EN_GARE"));
            trainRepository.save(trains.get(i));
            log.info(String.valueOf(trains.get(i).toString()));
        }

    }


}
