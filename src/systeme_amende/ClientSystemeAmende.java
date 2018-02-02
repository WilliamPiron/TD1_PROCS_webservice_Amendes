package systeme_amende;

import listes.ListeAmendeSingleton;
import listes.ListePersonneSingleton;
import listes.ListeVoituresSingleton;
import model.Amende;
import model.Personne;
import model.Voiture;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class ClientSystemeAmende{

    static public void main(String[] args) throws Exception{
        SystemeAmendes sysam;
        URL url = new URL("http://localhost:9999/ws/systemeamendes?wsdl");
        QName qname = new QName("http://systeme_amende/", "SystemeAmendesImplService");
        Service service = Service.create(url, qname);
        sysam = service.getPort(SystemeAmendes.class);

        ListeAmendeSingleton listeAmendes = ListeAmendeSingleton.getInstance();
        ListeVoituresSingleton listeVoitures = ListeVoituresSingleton.getInstance();
        ListePersonneSingleton listePersonnes = ListePersonneSingleton.getInstance();

        int numeroUn;
        int numeroDeux;
        int numeroMoinsUn;

        Amende[] listeAmendesClio3;

        listePersonnes.enregistrerPersonne(new Personne("Sponge","Bob",""));
        listePersonnes.enregistrerPersonne(new Personne("Haddock","Captain",""));

        Voiture clioBob = new Voiture("AB 123 CD", "Clio 3");
        clioBob.setProprietaire(listePersonnes.getPersonneFromList("Sponge","Bob"));
        sysam.enregistrer(clioBob);

        Voiture puntoHaddock = new Voiture("AB 124 CD", "Punto");
        clioBob.setProprietaire(listePersonnes.getPersonneFromList("Haddock","Captain"));
        sysam.enregistrer(puntoHaddock);

        numeroUn = sysam.signaler("AB 123 CD", "Clio 3", 90);
        numeroMoinsUn = sysam.signaler("AB 123 CD", "Tracteur", 90);
        numeroDeux = sysam.signaler("AB 123 CD", "Clio 3", 140);

        listeAmendesClio3 = sysam.lister("AB 123 CD");

        sysam.payer(1, "Haddock", "Captain");

        listeAmendesClio3 = sysam.lister("AB 123 CD");

        sysam.payer(1, "Sponge", "Bob");

        listeAmendesClio3 = sysam.lister("AB 123 CD");

    }


}

/*
enregistrement d'une Clio 3 immatriculée AB 123 CD possédée par Bob Sponge VVVV
enregistrement d'un Punto immatriculée AB 124 CD possédée par Capitaine Haddock VVVV
signalement d'une infraction à 90€ pour une Clio 3 immatriculée AB 123 CD (renvoit numéro n1)
signalement d'une infraction à 90€ pour un Tracteur immatriculé AB 123 CD (fausse plaque, renvoit -1)
signalement d'une infraction à 140€ pour une Clio 3 immatriculée AB 123 CD (renvoit numéro n2)
listing des amendes de la voiture AB 123 CD (nombre : 2)
paiement de l'amende n1 par Capitaine Haddock (non pris en compte)
listing des amendes de la voiture AB 123 CD (nombre : 2)
paiement de l'amende n1 par Bob Sponge
listing des amendes de la voiture AB 123 CD (nombre : 1)
 */
