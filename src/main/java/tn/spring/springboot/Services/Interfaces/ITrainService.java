package tn.spring.springboot.Services.Interfaces;

import tn.spring.springboot.entities.Train;

import java.text.ParseException;
import java.util.List;

public interface ITrainService {
    public Train ajouterTrain(Train train);

    public List<Train> listTrain();

    public void affecterTrainAGare(Long idTrain, Long idGareDepart, Long idGareArrivee);

    public void affecterTainAClient(Long idClient, Long idGareDepart);

    public int TrainPlacesLibres(Long idGareDepart);

    public List<Train> ListerTrainsIndirects(Long idGareDepart, Long idGareArrivee);

    public void DesaffecterClientsTrain(Long idGareDepart, double heureDepart);

    public void TrainsEnGare() throws ParseException;

}
