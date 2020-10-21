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
        boolean t;
        for (Konto value : KontoList) {
            user.setPassord(pass);
            t = value.getPassord().equals(pass);
            //System.out.println(value.getNavn() + " and " + input + " is " + t);
            if (t) {

                Assert.assertEquals(value.getPassord(), user.getPassord());
                System.out.println("Password " + user.getPassord() + " exists");
                break;
            } else {
                Assert.assertFalse(false);
                //System.out.println("Username " + user.getNavn() + " does not exist");
            }
        }
    }
}
