import KontoInformasjon.Konto;
import Repo.JSONRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class Username {
    Konto user = new Konto();
    ArrayList<Konto> KontoList = new ArrayList<>();
    JSONRepo konto = new JSONRepo();

    @ParameterizedTest
    @ValueSource(strings = {"Yaqub","Aleks","Robin","kar","SSs"})
    public void testUsername(String input) {
        KontoList = konto.LoadFileKonto("Konto.json");
        boolean t = false;
        for (Konto value : KontoList) {
            user.setNavn(input);

            //System.out.println(value.getNavn() + " and " + input + " is " + t);
            if(value.getNavn().equals(input)){
                t = true;
                Assert.assertEquals(value.getNavn(), user.getNavn());
                System.out.println("Username " + user.getNavn() + " exists");
                break;
                }
            }
        if(!t){
            Assert.assertFalse(false);
            System.out.println("Username " + user.getNavn() + " does not exist");
        }
    }
}

