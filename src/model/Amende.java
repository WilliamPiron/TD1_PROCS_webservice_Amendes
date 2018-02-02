package model;

import java.io.Serializable;

public class Amende implements Serializable {
    private static int numero_ = 0;
    private int numero;
    private String immatriculation;
    private int tarif;

    public Amende() { super(); }

    public Amende(String immatriculation, int tarif) {
        numero_++;
        numero = numero_;
        this.immatriculation = immatriculation;
        this.tarif = tarif;
    }

    public int getNumero() {
        return numero;
    }
    public String getImmatriculation() {
        return immatriculation;
    }
    public int getTarif() { return tarif; }

    public void setNumero(int numero) { this.numero = numero; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public void setTarif(int tarif) { this.tarif = tarif; }

    @Override
    public String toString() {
        return String.format("Amende n°%d de %d EUR pour %s",numero,tarif,immatriculation);
    }
}
