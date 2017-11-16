package entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by user on 09/06/16.
 */
@XmlRootElement
public class Fidelite {

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
