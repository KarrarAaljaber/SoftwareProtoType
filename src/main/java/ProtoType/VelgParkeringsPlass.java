package ProtoType;

import Parkeringsplass.Parkeringsplass;
import Repo.JSONRepo;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class VelgParkeringsPlass {


    private Scene scene;
    private Pane container;
    private StackPane root;
    private GridPane choosePane;
    private Button confirmSelect;






    private final double intialBlur = 4;
    private BoxBlur blur = new BoxBlur(intialBlur, intialBlur, 5);





    //velg parkeringsplass

    private ArrayList<Parkeringsplass> parkeringsplasser;

    private ArrayList<RadioButton> buttons = new ArrayList<>();
    private ToggleGroup radioGroup;
    private ArrayList<RadioButton> velgParkRadio = new ArrayList<>();

   private ArrayList<Text> parkeringsnavner = new ArrayList<>();
    private ArrayList<Text> adresser = new ArrayList<>();
    private ArrayList<Text> priser = new ArrayList<>();
    private ArrayList<Text> ledigplasser = new ArrayList<>();


    Color[] colors = {
            new Color(0.0, 0.9, 0.0, 1.0).saturate().brighter(),
            new Color(0.9, 0.0, 0.0, 1.0).saturate().brighter(),
            new Color(0.5, 0.5, 0.0, 1.0).saturate().brighter(),
            new Color(0.5, 0.9, 0.0, 1.0).saturate().brighter(),
            new Color(0.9, 0.5, 0.0, 1.0).saturate().brighter()
    };

    private Stage stage;
    private LaunchProtoType lp;
    public VelgParkeringsPlass(Stage stage, LaunchProtoType lp){
        this.stage = stage;
        this.lp = lp;
        root = new StackPane();
        container = new Pane();
        choosePane = new GridPane();
        choosePane.setId("choosePane");
        choosePane.setAlignment(Pos.CENTER);
        choosePane.setHgap(10);
        choosePane.setMaxSize(800, 400);
        choosePane.toFront();
        root.getChildren().add(container);
        root.getChildren().add(choosePane);
        container.setStyle("-fx-background-color: rgba(22,22,22,1);");

        scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

        initParkingplasser();
        initPaneBakgrunn();


    }

    private JSONRepo repo;
    public void initParkingplasser() {
        parkeringsplasser = new ArrayList<>();

        repo = new JSONRepo();
        //  repo.WriteToJSON("parkeringsplasser.json", parkeringsplasser);
        velgPark();




        //laster opp de forskjellige parkeringsplassene
        for (int i = 0; i < parkeringsplasser.size(); i++) {
            Text ParkeringsNavni = new Text("ParkeringsNavn: " + parkeringsplasser.get(i).getParkeringnavn());
            ParkeringsNavni.setId("text");
            ParkeringsNavni.setWrappingWidth(0);
            parkeringsnavner.add(ParkeringsNavni);

            Text adressei = new Text("Adresse: " + parkeringsplasser.get(i).getAdresse());
            adressei.setId("text");
            adressei.setWrappingWidth(0);
            adresser.add(adressei);

            Text prisi = new Text("Pris Per Time: " + parkeringsplasser.get(i).getPris());
            prisi.setId("text");
            prisi.setWrappingWidth(0);
            priser.add(prisi);

            Text LedigePlasseri = new Text("Antall Ledige Plasser: " + parkeringsplasser.get(i).getPlasser());
            LedigePlasseri.setId("text");
            LedigePlasseri.setWrappingWidth(0);
            ledigplasser.add(LedigePlasseri);

        }


        blur.setInput(new ColorAdjust(0, 0, 0.4, 0));




        confirmSelect.setOnAction(action -> {


            UserView view = new UserView(stage, this, lp);
            view.initParkeringsplasser();
        });

    }





    public void velgPark(){
        //laster opp parkeringsplassene fra en json fil
        parkeringsplasser = repo.LoadFile("parkeringsplasser.json");
        radioGroup = new ToggleGroup();
        HBox radioBox = new HBox();
        Text text = new Text("ParkeringSteder I Naerheten");
        text.setStyle("-fx-font-size: 25px;");

        text.setFill(Color.WHITE);
        text.toFront();
        choosePane.add(text,0, 0);
     //   choosePane.setGridLinesVisible(true);





        //lager en radiobutton for hvert parkeringsplass
        for(int i=0; i < parkeringsplasser.size(); i++){
            ImageView Imagei = new ImageView(new Image(parkeringsplasser.get(i).getImgurl()));
            Imagei.setFitWidth(200);
            Imagei.setFitHeight(200);
            Imagei.setId("parkChooseImg");
            choosePane.add(Imagei, i , 1);
            RadioButton radioButtoni = new RadioButton(parkeringsplasser.get(i).getParkeringnavn());
            buttons.add(radioButtoni);
            radioButtoni.setToggleGroup(radioGroup);
            radioBox.getChildren().add(radioButtoni);
            velgParkRadio.add(radioButtoni);
            choosePane.add(radioButtoni, i , 2);


        }

        confirmSelect = new Button("Confirm");
        choosePane.add(confirmSelect, 0,3);
        choosePane.setPadding(new Insets(20,20,20,20));

    }





    public void initPaneBakgrunn(){
        int spawnNodes = 50;
        for(int i=0; i < spawnNodes; i++ ){
            spawnNode();
        }

    }

    public void spawnNode(){
        Circle node = new Circle();
        node.setManaged(false);
        node.setFill(new ImagePattern(new Image("https://www.shareicon.net/data/512x512/2015/11/06/667871_circle_512x512.png")));
        node.setCenterX(Math.random() * scene.getWidth());
        node.setCenterY(Math.random() * scene.getHeight());
        node.setRadius(Math.random() * 50);



        Light.Point light = new Light.Point();
        Light.Point light2 = new Light.Point();

        light.setColor(colors[(int)(Math.random() * 4)]);
        light2.setColor(colors[(int)(Math.random() * 4)]);


        //Setting the position of the light
        light.setX(node.getCenterX());
        light.setY(node.getCenterY());
        light.setZ(node.getCenterX());


        light2.setX(node.getCenterX());
        light2.setY(node.getCenterY());
        light2.setZ(node.getCenterX());

        //Instantiating the Lighting class

        Lighting[] lights = {
                new Lighting(),
                new Lighting() };


        //Setting the light
        lights[0].setLight(light);
        lights[0].setSpecularConstant(Math.random() * 15);

        lights[1].setLight(light2);
        lights[1].setSpecularConstant(Math.random() * 15);



        TranslateTransition transition= new TranslateTransition();
        transition.setDuration(Duration.seconds(20));
        transition.setRate(2);
        transition.setToX(Math.random() * scene.getHeight());
        transition.setToY(Math.random() * scene.getHeight());
        transition.setAutoReverse(true);
        node.setEffect(lights[(int)(Math.random() * 2)]);

        transition.setCycleCount(Animation.INDEFINITE);
        transition.setNode(node);
        transition.play();
        container.getChildren().add(node);

    }


    public Scene getScene(){return scene;}

    public ArrayList<Parkeringsplass> getParkeringsplasser() {
        return parkeringsplasser;
    }

    public ArrayList<RadioButton> getButtons() {
        return buttons;
    }

    public ToggleGroup getRadioGroup() {
        return radioGroup;
    }

    public ArrayList<RadioButton> getVelgParkRadio() {
        return velgParkRadio;
    }

    public ArrayList<Text> getParkeringsnavner() {
        return parkeringsnavner;
    }

    public ArrayList<Text> getAdresser() {
        return adresser;
    }

    public ArrayList<Text> getPriser() {
        return priser;
    }

    public ArrayList<Text> getLedigplasser() {
        return ledigplasser;
    }
}
