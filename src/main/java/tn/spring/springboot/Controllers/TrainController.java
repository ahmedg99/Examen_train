package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.Services.Interfaces.ITrainService;
import tn.spring.springboot.entities.Gare;
import tn.spring.springboot.entities.Train;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/Train")
public class TrainController {


    @Autowired
    ITrainService iTrainService ;

    @PostMapping("/add")
    @ResponseBody
    public void addTrain(@RequestBody Train train) {
        iTrainService.ajouterTrain(train);
    }



    @GetMapping(value = "/list")
    @ResponseBody
    public List<Train> listGares() {
        return iTrainService.listTrain() ;
    }



    @PutMapping(value = "/affectertrainauxgares/{trainid}/{garedepartid}/{garearriveeid}")
    @ResponseBody
    public void affectertrainauxgares(@PathVariable("trainid") Long trainid ,@PathVariable("garedepartid") Long garedepartid,@PathVariable("garearriveeid") Long garearriveeid ) {
        iTrainService.affecterTrainAGare(trainid,garedepartid,garearriveeid);
    }

    @PutMapping(value = "/affecterclientautrain/{clientid}/{garedepartid}")
    @ResponseBody
    public void affecterclientautrain(@PathVariable("clientid") Long clientid ,@PathVariable("garedepartid") Long garedepartid ) {
        iTrainService.affecterTainAClient(clientid,garedepartid);
    }



    @GetMapping(value = "/avgplaceslibres/{garedepartid}")
    @ResponseBody
    public int avgplaceslibres(@PathVariable("garedepartid") Long garedepartid) {
        return iTrainService.TrainPlacesLibres(garedepartid) ;
    }


    @PutMapping(value = "/DesaffecterClientsTrain/{garedepartid}/{heure}")
    @ResponseBody
    public void affecterclientautrain(@PathVariable("garedepartid") Long garedepartid,@PathVariable("heure") Double heure ) {
        iTrainService.DesaffecterClientsTrain(garedepartid,heure);
    }




    @GetMapping(value = "/ArrivalDateBeforeDateSystem")
    public void ArrivalDateBeforeDateSystem() throws ParseException {
        iTrainService.TrainsEnGare() ;
    }



    @GetMapping(value = "/ListerTrainsIndirects/{garedepartid}/{garearriveeid}")
        public List<Train> ListerTrainsIndirects(@PathVariable("garedepartid") Long garedepartid ,@PathVariable("garearriveeid") Long garearriveeid  ) throws ParseException {
        return iTrainService.ListerTrainsIndirects(garedepartid, garearriveeid);
    }


}
