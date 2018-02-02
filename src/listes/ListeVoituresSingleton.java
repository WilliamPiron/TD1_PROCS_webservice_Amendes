package listes;

import model.Voiture;

import java.util.ArrayList;

public class ListeVoituresSingleton {
    private ArrayList<Voiture> listeVoitures;

    private static ListeVoituresSingleton instance = new ListeVoituresSingleton();

    public static ListeVoituresSingleton getInstance() {
        return instance;
    }

    private ListeVoituresSingleton() {
        listeVoitures = new ArrayList<>();
    }

    public void enregistrerVoiture(Voiture voiture){
        listeVoitures.add(voiture);
    }

    public Voiture getVoitureFromList(String immatriculation){
        for(Voiture voiture : listeVoitures){
            if(voiture.getImmatriculation().equals(immatriculation)){
                return voiture;
            }
        }
        return null;
    }
}
