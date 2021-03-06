package systeme_amende;

import listes.ListeAmendeSingleton;
import listes.ListePersonneSingleton;
import listes.ListeVoituresSingleton;
import model.Amende;
import model.Personne;
import model.Voiture;

import javax.jws.WebService;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebService(endpointInterface = "systeme_amende.SystemeAmendes")
public class SystemeAmendesImpl implements SystemeAmendes{

    private ListeAmendeSingleton listeAmendes = ListeAmendeSingleton.getInstance();
    private ListeVoituresSingleton listeVoitures = ListeVoituresSingleton.getInstance();
    private ListePersonneSingleton listePersonnes = ListePersonneSingleton.getInstance();

    @Override
    public void enregistrer(Voiture v) {
        listeVoitures.enregistrerVoiture(v);
        System.out.println("Voiture enregistrée !");
        System.out.println(v.toString());

    }

    @Override
    public int signaler(String immatriculation, String modele, int tarif) {
        Voiture voiture = listeVoitures.getVoitureFromList(immatriculation);

        if (voiture.getImmatriculation().equals(immatriculation) && voiture.getModele().equals(modele)){
            Amende amende = new Amende(immatriculation, tarif);
            listeAmendes.addAmende(amende);
            System.out.println("Amende enregistrée !");
            System.out.println(amende.toString());
            return amende.getNumero();
        }
        else
            return -1;
    }

    @Override
    public Amende[] lister(String immatriculation) {
        System.out.println("Liste des amendes pour " + immatriculation + " : ");
        Voiture voiture = listeVoitures.getVoitureFromList(immatriculation);
        ArrayList<Amende> amendes = listeAmendes.getAmendesFromVoiture(voiture);

        Amende[] res = new Amende[Array.getLength(amendes)];
        amendes.toArray(res);

        for(int i = 0; i < res.length; i++){
            System.out.println(res[i].toString());
        }

        return res;
    }

    @Override
    public void payer(int numero, String nom, String prenom) {
        Personne aPaye = listePersonnes.getPersonneFromList(nom, prenom);
        Amende amendeAPayer = listeAmendes.getAmendeByNumero(numero);
        String immatriculationAmende = amendeAPayer.getImmatriculation();
        Voiture voiture = listeVoitures.getVoitureFromList(immatriculationAmende);

        if (voiture.getProprietaire().equals(aPaye)){
            listeAmendes.removeAmende(numero);
        }
    }


}
