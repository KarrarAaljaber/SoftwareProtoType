import KontoInformasjon.Konto;
import Parkeringsplass.Bestilling;
import ProtoType.BestillingView;
import Repo.JSONRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class checkBestilling extends CheckOmraadeNavn {

    Konto bruker = new Konto("Aleks", "123");
    Bestilling test = new Bestilling(bruker, 1, "Høgskolen i Øsfold", "navn", "003213",19, 20,20,20);
    JSONRepo bestilling = new JSONRepo();
    ArrayList<Bestilling> Liste = new ArrayList<>();

    @Test
    public void registerBestilling(){
        Liste = bestilling.LoadFileBestillinger("bestillinger.json");
        Liste.add(test);
        for (Bestilling value : Liste) {
            if (test.equals(value)) {
                Assert.assertEquals(value, test);
                System.out.println("Bestilling exist");
            }
        }
    }




}
