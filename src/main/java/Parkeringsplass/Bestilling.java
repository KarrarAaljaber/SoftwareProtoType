package Parkeringsplass;

import KontoInformasjon.Konto;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bestilling {

    private Konto bruker;
    private int rutenr;
    private String parkeringsplassnavn;
    private String fullnavn;
    private String tlfnr;
    private int fraTime, fraMinut;
    private int tilTime, tilMinut;

    public Bestilling(Konto bruker, int rutenr, String parkeringsplassnavn, String fullnavn, String tlfnr, int fraTime, int fraMinut, int tilTime, int tilMinut) {
        this.bruker = bruker;
        this.rutenr = rutenr;
        this.parkeringsplassnavn = parkeringsplassnavn;
        this.fullnavn = fullnavn;
        this.tlfnr = tlfnr;
        this.fraTime = fraTime;
        this.fraMinut = fraMinut;
        this.tilTime = tilTime;
        this.tilMinut = tilMinut;
    }

    public Bestilling(){

    }

    public Konto getBruker() {
        return bruker;
    }

    public int getRutenr() {
        return rutenr;
    }

    public String getParkeringsplassnavn() {
        return parkeringsplassnavn;
    }

    public String getFullnavn() {
        return fullnavn;
    }

    public String getTlfnr() {
        return tlfnr;
    }

    public int getFraTime() {
        return fraTime;
    }

    public int getFraMinut() {
        return fraMinut;
    }

    public int getTilTime() {
        return tilTime;
    }

    public int getTilMinut() {
        return tilMinut;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Bestilling{" +
                "bruker=" + bruker +
                ", rutenr=" + rutenr +
                ", parkeringsplassnavn=" + parkeringsplassnavn +
                ", fullnavn='" + fullnavn + '\'' +
                ", tlfnr='" + tlfnr + '\'' +
                ", fraTime=" + fraTime +
                ", fraMinut=" + fraMinut +
                ", tilTime=" + tilTime +
                ", tilMinut=" + tilMinut +
                '}';
    }
}

