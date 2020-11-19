package Repo;


import KontoInformasjon.Konto;
import Parkeringsplass.Bestilling;

import java.util.ArrayList;

public interface CRUD {

    ArrayList<Bestilling> deleteBestilling(int rutenr);

    ArrayList<Konto> addKonto(String navn, String passord);
}
