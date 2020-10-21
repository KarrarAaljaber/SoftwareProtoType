import Repo.JSONRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class kontoTest {
    KontoInformasjon.Konto user = new KontoInformasjon.Konto();
    ArrayList<KontoInformasjon.Konto> KontoList = new ArrayList<>();
    JSONRepo konto = new JSONRepo();
    @Test
    public void KontoFinnes(){
        KontoList = konto.LoadFile2("Konto.json");
        user.setNavn("Aleks");
        user.setPassord("123");
        boolean t = false;
        for (KontoInformasjon.Konto value : KontoList) {
            if (user.getNavn().equals(value.getNavn()) && user.getPassord().equals(value.getPassord())) {
                t = true;
                Assert.assertTrue("Konto finnes", t);
                System.out.println("Konto " + user.getNavn() + " med passord " + user.getPassord() + " finnes!");
                break;
            }
        }
        if(!t) {
            Assert.fail("Konto finnes ikke");
        }
    }
}