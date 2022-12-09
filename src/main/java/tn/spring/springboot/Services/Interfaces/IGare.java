package tn.spring.springboot.Services.Interfaces;

import tn.spring.springboot.entities.Gare;

import java.util.List;

public interface IGare {
    public Gare addGare(Gare gare);
    public List<Gare> ListeGare() ;
 }
