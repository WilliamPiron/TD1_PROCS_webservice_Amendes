package listes;

import model.Personne;

import java.util.ArrayList;

public class ListePersonneSingleton {
    private ArrayList<Personne> listePersonnes;

    private static ListePersonneSingleton instance = new ListePersonneSingleton();

    public static ListePersonneSingleton getInstance() {
        return instance;
    }

    private ListePersonneSingleton() {
        listePersonnes = new ArrayList<>();
    }

    public void enregistrerPersonne(Personne personne){
        listePersonnes.add(personne);
    }

    public Personne getPersonneFromList(String nom, String prenom){
        for(Personne personne : listePersonnes){
            if(personne.getNom().equals(nom) && personne.getPrenom().equals(prenom)){
                return personne;
            }
        }
        return null;
    }
}
