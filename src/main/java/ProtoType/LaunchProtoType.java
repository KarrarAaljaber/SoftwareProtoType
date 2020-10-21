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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class LaunchProtoType extends Application {

    Konto Test = new Konto("Name", "passord");
    JSONRepo rep = new JSONRepo();
    ArrayList<Konto> KontoList = new ArrayList<>();
    public LaunchProtoType(){
        KontoList.add(Test);
        KontoList = rep.LoadFile2("Konto.json");
        //rep.WriteToJSON2("Konto.json",KontoList);
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



        grid.setAlignment(Pos.CENTER);
        grid.add(nameLabel,0,0);
        grid.add(name, 1,0);
        grid.add(passLabel, 0,1);
        grid.add(pass, 1, 1);

        grid.add(adminLabel,0, 2);
        grid.add(radioBox, 1, 2);
        grid.add(login, 2, 2);
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

                UserView view = new UserView(stage);


            } else {
                text.setVisible(true);
            }
        }

        });


        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();

    }



    public static void main(String[]args){
        launch(args);
    }

}
