package KontoInformasjon;

public class Konto {


    private String navn, passord;


    public Konto(String navn, String passord){
        this.navn = navn;
        this.passord = passord;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
