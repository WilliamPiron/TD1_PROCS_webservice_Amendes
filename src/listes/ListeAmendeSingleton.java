package listes;

import model.Amende;
import model.Voiture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListeAmendeSingleton {
    private static ListeAmendeSingleton instance;
    private Map<Integer, Amende> listeAmende;

    private ListeAmendeSingleton(){
        listeAmende = new HashMap<>();
    }

    public static ListeAmendeSingleton getInstance(){
        if (instance == null){
            instance = new ListeAmendeSingleton();
        }
        return instance;
    }

    public void addAmende(Amende amende){
        listeAmende.put(amende.getNumero(), amende);
    }

    public ArrayList<Amende> getAmendesFromVoiture(Voiture voiture){
        ArrayList<Amende> res = new ArrayList<>();
        for (int i = 0; i < listeAmende.size(); i++){
            if (listeAmende.get(i).getImmatriculation() == voiture.getImmatriculation()){
                res.add(listeAmende.get(i));
            }
        }

        return res;
    }

    public Amende getAmendeByNumero (int numero){
        return listeAmende.get(numero);
    }

    public void removeAmende(int numero){
        listeAmende.remove(numero);
    }
}
