package ProtoType;

import KontoInformasjon.Konto;
import Repo.JSONRepo;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class LaunchProtoType extends Application {

    JSONRepo rep = new JSONRepo();
    ArrayList<Konto> KontoList = new ArrayList<>();
    private Konto loggedon;
    public LaunchProtoType(){
        KontoList = rep.LoadFileKonto("Konto.json");
        //rep.WriteToJSONKonto("Konto.json",KontoList);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ProtoType");
        GridPane grid = new GridPane();


        Scene scene = new Scene(grid, 1280, 720);


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


        //are you admin?
        RadioButton yes = new RadioButton("YES");
        RadioButton no = new RadioButton("NO");
        HBox radioBox = new HBox();
        radioBox.getChildren().addAll(yes, no);
        Label  adminLabel  = new Label("Are you admin?");

        ToggleGroup radioGroup = new ToggleGroup();
        yes.setToggleGroup(radioGroup);
        no.setToggleGroup(radioGroup);


        //Login Button
        Button login = new Button("Login");
        login.setMinSize(100,25);

        //registering
        Label regLabel = new Label("Dont have an  account? ");
        Button reg = new Button("Register");


        grid.setAlignment(Pos.CENTER);
        grid.add(nameLabel,0,0);
        grid.add(name, 1,0);
        grid.add(passLabel, 0,1);
        grid.add(pass, 1, 1);

        grid.add(adminLabel,0, 2);
        grid.add(radioBox, 1, 2);
        grid.add(login, 2, 2);
        grid.add(regLabel, 0, 3 );
        grid.add(reg, 1,3);

        grid.setVgap(5);
        grid.setId("grid");

        Text text = new Text("The Username or password is wrong");
        grid.add(text,0, 3);
        text.setVisible(false);

        login.setOnAction(action ->{

        for (int i =0; i<KontoList.size(); i++) {
            if(  name.getText().equals(KontoList.get(i).getNavn()) &&  pass.getText().equals(KontoList.get(i).getPassord())){
                System.out.println("CORrect");
                text.setVisible(false);
                loggedon = new Konto(KontoList.get(i).getNavn(), KontoList.get(i).getPassord());


                VelgParkeringsPlass vp = new VelgParkeringsPlass(stage, this);
                stage.setScene(vp.getScene());


            } else {
                text.setVisible(true);
            }
        }

        });
        reg.setOnAction(action ->{
            RegisterView regview = new RegisterView(stage, this);
            stage.setScene(regview.getScene());
        });


        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();

    }



    public static void main(String[]args){
        launch(args);
    }

    public Konto getLoggedon() {
        return loggedon;
    }

    public void setLoggedon(Konto loggedon) {
        this.loggedon = loggedon;
    }
}
