package ProtoType;

import KontoInformasjon.Konto;
import Repo.JSONRepo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RegisterView {
    private Scene scene;


    JSONRepo rep = new JSONRepo();
    ArrayList<Konto> KontoList = new ArrayList<>();
    private Stage stage;
    public RegisterView(Stage stage){
        this.stage = stage;
        KontoList = rep.LoadFileKonto("Konto.json");
        GridPane grid = new GridPane();
        scene = new Scene(grid, 1280, 720);
        scene.getStylesheets().add("style.css");

        //Name
        TextField name = new TextField();
        Label nameLabel = new Label("Username: ");
        nameLabel.setStyle(" -fx-font: 30px Tahoma;\n" +
                "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                "    -fx-stroke: black;\n" +
                "    -fx-stroke-width: 1;");

        nameLabel.setLabelFor(name);


        //password
        PasswordField pass = new PasswordField();
        Label passLabel = new Label("Password: ");
        passLabel.setStyle("  -fx-font: 30px Tahoma;\n" +
                "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                "    -fx-stroke: black;\n" +
                "    -fx-stroke-width: 1;");
        passLabel.setLabelFor(pass);

        Button reg = new Button("Register");

        grid.setAlignment(Pos.CENTER);
        grid.add(nameLabel,0,0);
        grid.add(name, 1,0);
        grid.add(passLabel, 0,1);
        grid.add(pass, 1, 1);
        grid.add(reg, 0,2);
        grid.setVgap(5);
        grid.setId("grid");
        Text text = new Text("The Username or password cant be empty");
        grid.add(text,0, 3);
        text.setVisible(false);
        reg.setOnAction(action ->{

            if(!name.getText().equals("") || !pass.getText().equals("") ) {
                KontoList = rep.addKonto(name.getText(), pass.getText());
                rep.WriteToJSONKonto("Konto.json",KontoList);
                VelgParkeringsPlass vp = new VelgParkeringsPlass(stage);
                stage.setScene(vp.getScene());

            }else{
                text.setVisible(true);
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
