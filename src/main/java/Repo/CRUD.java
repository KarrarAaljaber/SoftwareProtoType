package Repo;


import KontoInformasjon.Konto;

import java.util.ArrayList;

public interface CRUD {


    ArrayList<Konto> addKonto(String navn, String passord);
}
