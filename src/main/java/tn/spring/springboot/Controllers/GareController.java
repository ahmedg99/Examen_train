package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.Services.Interfaces.IGare;
import tn.spring.springboot.entities.Gare;

import java.util.List;

@RestController
@RequestMapping("/Gare")
public class GareController {


    @Autowired
    IGare iGare;

    @PostMapping("/add")
    @ResponseBody
    public void addGare(@RequestBody Gare gare) {
        iGare.addGare(gare);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public List<Gare> listGares() {
        return iGare.ListeGare();
    }


}
