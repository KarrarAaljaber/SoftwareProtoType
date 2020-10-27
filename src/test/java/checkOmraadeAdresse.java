import Parkeringsplass.Parkeringsplass;
import Repo.JSONRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

public class checkOmraadeAdresse {
    JSONRepo repo = new JSONRepo();
    ArrayList<Parkeringsplass> parkeringsplasser = new ArrayList<>();
    Parkeringsplass plass = new Parkeringsplass("","Walkers gate 4, 1771 Halden",0,0,"");

    @Test
    public void omraadeAdresse(){
        parkeringsplasser = repo.LoadFile("parkeringsplasser.json");
        boolean t = false;
        for (Parkeringsplass parkeringsplass : parkeringsplasser) {
            if (plass.getAdresse().equals(parkeringsplass.getAdresse())) {
                t = true;
                Assert.assertTrue(t);
                System.out.println("Adresse Exist");
                break;
            }
        }
        if(!t){
            Assert.assertFalse(t);
            System.out.println("Adresse does not exist");
        }

    }

}
