import Parkeringsplass.Parkeringsplass;
import Repo.JSONRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

public class CheckOmraader {
    JSONRepo repo = new JSONRepo();
    ArrayList<Parkeringsplass> parkeringsplasser = new ArrayList<>();
    Parkeringsplass plass = new Parkeringsplass("Halden Tista Sentrum");

    @Test
    public void omraade(){
        parkeringsplasser = repo.LoadFile("parkeringsplasser.json");
        boolean t = false;
        for (Parkeringsplass parkeringsplass : parkeringsplasser) {
            if (plass.getParkeringnavn().equals(parkeringsplass.getParkeringnavn())) {
                t = true;
                Assert.assertTrue("Parkeringsplass Exist", t);
                System.out.println("Parkeringsplass Exist");
                break;
            }
        }
    if(!t){
        Assert.assertFalse(false);
        System.out.println("Parkeringsplass does not exist");
    }

    }

}
