package fr.polytechnice.caddyhop.models;

import java.io.Serializable;

/**
 * Created by shafiq on 11/06/16.
 */

public class Fidelite implements Serializable {
    private String nomMag;
    private String codeFidel;

    public Fidelite(){}

    public Fidelite(String nomMag, String codeFidel){
        this.codeFidel=codeFidel;
        this.nomMag=nomMag;
    }

    public String getNomMag() {
        return nomMag;
    }

    public String getCodeFidel() {
        return codeFidel;
    }

    public void setNomMag(String nomMag) {
        this.nomMag = nomMag;
    }

    public void setCodeFidel(String codeFidel) {
        this.codeFidel = codeFidel;
    }

    @Override
    public String toString() {
        return "Fidelite{" +
                "nomMag='" + nomMag + '\'' +
                ", codeFidel='" + codeFidel + '\'' +
                '}';
    }
}
