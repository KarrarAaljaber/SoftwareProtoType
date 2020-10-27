package Parkeringsplass;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.params.ParameterizedTest;


public class Parkeringsplass {

    private int plasser;
    private String adresse;
    private float pris;
    private String parkeringnavn;
    private String imgurl;

    public Parkeringsplass(String parkeringnavn, String adresse, float pris, int plasser , String imgurl){
        this.parkeringnavn = parkeringnavn;
        this.imgurl = imgurl;
        this.adresse = adresse;
        this.pris = pris;
        this.plasser = plasser;
    }
    public Parkeringsplass(String parkeringnavn){
        this.parkeringnavn = parkeringnavn;
    }
    public Parkeringsplass(){

    }


    public int getPlasser() {
        return plasser;
    }

    public void setPlasser(int plasser) {
        this.plasser = plasser;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getPris() {
        return pris;
    }

    public void setPris(float pris) {
        this.pris = pris;
    }

    public String getParkeringnavn() {
        return parkeringnavn;
    }

    public void setParkeringnavn(String parkeringnavn) {
        this.parkeringnavn = parkeringnavn;
    }

    public String getImgurl() {
        return imgurl;
    }

    @JsonIgnore
    @Override
    public String toString(){
        return new String("Parkering's navn: " + getParkeringnavn()+ "\n" +
                "adresse: " + getAdresse() + "\n" + "pris: " + getPris() + "\n" +
                "Antall parkering's spotter: " + plasser + "\n" + "imgurl: " + imgurl  );
    }
}
