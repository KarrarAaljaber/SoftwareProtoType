import KontoInformasjon.Konto;
import ProtoType.RegisterView;
import Repo.JSONRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class checkRegister {
    RegisterView test = new RegisterView();
    JSONRepo brukerkonto = new JSONRepo();
    ArrayList<Konto> kontoer = new ArrayList<>();
    @Test
    public void checkIfExist(){
      kontoer = brukerkonto.LoadFileKonto("Konto.json");
      test.setUsername("Test1");
      test.setPassword("123");
      boolean t = false;
        for (Konto konto : kontoer) {
            if (test.getUsername().equals(konto.getNavn())) {
                t = true;
                System.out.println("Konto exist");
                Assert.assertTrue(t);
                break;
            }

        }
        if(!t){
            Assert.assertFalse(t);
            System.out.println("Konto does not exist");
            System.out.println("Adding Konto to json file");
            kontoer = brukerkonto.addKonto(test.getUsername(), test.getPassword());
            brukerkonto.WriteToJSONKonto("konto.json", kontoer);
        }
    }
}
