package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.Services.Interfaces.IClientService;
import tn.spring.springboot.entities.Client;

import java.util.List;

@RestController
@RequestMapping("/Client")

public class ClientController {


    @Autowired
    IClientService iClientService;

    @GetMapping(value = "/list")
    @ResponseBody
    public List<Client> getAllEtudiants() {
        return iClientService.afficherClients();
    }


    @PostMapping("/add")
    @ResponseBody
    public void addGare(@RequestBody Client client) {
        iClientService.AjouterClient(client);
    }


}
