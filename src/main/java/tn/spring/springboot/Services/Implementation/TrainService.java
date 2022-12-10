package tn.spring.springboot.Services.Implementation;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        Gare garedepart = gareRepository.findById(idGareDepart).get() ;
        Train train = trainRepository.findByGareDepartandAndGareArriveeAndHeureArrivee();
        Client client = clientRepository.findById(idClient).get() ;
        //System.out.println(train);

        if(train.getNbPlaceLibre()>0) {
            client.setTrain(train);
            clientRepository.save(client);
            train.setNbPlaceLibre((train.getNbPlaceLibre()-1));
            log.info("client ajoutée au train ");
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

        Gare garedepart = gareRepository.findById(idGareDepart).get();
        Gare garearrivee= gareRepository.findById(idGareArrivee).get();
        log.info((trainRepository.trainindirectes(garearrivee.getNom()).toString()));
        List<Train> listetrain = trainRepository.trainindirectes(garearrivee.getNom()) ;
        return listetrain.stream().filter(e->!(e.getGareDepart().getNom().equals(garedepart.getNom()))).collect(Collectors.toList());
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
    @Override
    public void TrainsEnGare()  {
        /*
        Date curretndate = new Date();
        SimpleDateFormat  formater = new SimpleDateFormat("yyyy-MM-dd");
         String datecurretn = formater.format(curretndate);
         Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datecurretn);
         // List<Train> liste = trainRepository.findAll();

       List<Train> liste =  trainRepository.findAll().stream().filter(e->

           e.getDateArrivee().before(date)).collect(Collectors.toList());
       liste.forEach(t->{ log.info("la liste des train qui ont une date d'arrivée avant la date system sont  :  " + t.toString()  );});
       // log.info(String.valueOf(date.before(new SimpleDateFormat("yyyy-MM-dd").parse("2001-02-01"))));
*/
        List<Train> trains = trainRepository.findTrainByDateArriveeBeforeee() ;

        for(int i=0 ; i< trains.size();i++) {
            log.info(String.valueOf(trains.get(i).toString()));
        }

    }


}
