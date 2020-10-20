import KontoInformasjon.Konto;
import ProtoType.LaunchProtoType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Username {
    Konto user = new Konto("Test", "Test");

    @Test
    public void testUsername() {
        TextField name = new TextField();
        name.setText("Test");
        Assert.assertEquals(user.getNavn(), name.getText());
    }
    @Test
    public void testPassword(){
        PasswordField pass = new PasswordField();
        pass.setText("Test");
        Assert.assertEquals(user.getNavn(), pass.getText());
    }
}
