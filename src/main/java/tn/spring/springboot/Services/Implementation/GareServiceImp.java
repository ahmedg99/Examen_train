package tn.spring.springboot.Services.Implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IGare;
import tn.spring.springboot.entities.Gare;
import tn.spring.springboot.repositories.GareRepository;

import java.util.List;

@Service
@Slf4j
public class GareServiceImp implements IGare {


    @Autowired
    GareRepository gareRepository ;


    @Override
    public Gare addGare(Gare gare) {
        gareRepository.save(gare);
        log.info("gare ajoutée");
        return  gareRepository.save(gare);
    }

    @Override
    public List<Gare> ListeGare() {
        return gareRepository.findAll();
    }
}
