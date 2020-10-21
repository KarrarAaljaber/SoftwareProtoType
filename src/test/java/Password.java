import KontoInformasjon.Konto;
import Repo.JSONRepo;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class Password {
    Konto user = new Konto();
    ArrayList<Konto> KontoList = new ArrayList<>();
    JSONRepo konto = new JSONRepo();

    @ParameterizedTest
    @ValueSource(strings = {"123", "bag", "Test", "Gay", "SSs"})
    public void testPassword(String pass) {
        KontoList = konto.LoadFile2("Konto.json");
        boolean t = false;
        for (Konto value : KontoList) {
            user.setPassord(pass);

            //System.out.println(value.getNavn() + " and " + input + " is " + t);
            if (value.getPassord().equals(pass)) {
                t = true;
                Assert.assertEquals(value.getPassord(), user.getPassord());
                System.out.println("Password " + user.getPassord() + " exists");
                break;
            }
        }
        if(!t) {
            Assert.assertFalse(false);
            System.out.println("Password " + user.getPassord() + " does not exist");
        }
    }
}
