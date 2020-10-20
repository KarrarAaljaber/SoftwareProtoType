package ProtoType;

import Parkeringsplass.Parkeringsplass;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class UserView {

    private Scene scene;
    private GridPane pane;

    private Stage stage;

    private Parkeringsplass Haldensentrum;
    private Parkeringsplass hiof;
    private Pane container;





    private StackPane root;

    private GridPane infoPane;
    private GridPane choosePane;


    private ArrayList<RadioButton> velgParkRadio = new ArrayList<>();
    private  Button confirmSelect;

    Color[] colors = {
            new Color(0.0, 0.9, 0.0, 1.0).saturate().brighter(),
            new Color(0.9, 0.0, 0.0, 1.0).saturate().brighter(),
            new Color(0.5, 0.5, 0.0, 1.0).saturate().brighter(),
            new Color(0.5, 0.9, 0.0, 1.0).saturate().brighter(),
            new Color(0.9, 0.5, 0.0, 1.0).saturate().brighter()
    };

    enum ValgtParkeringPlass {
        tista,
        hiof,

    }
    private ValgtParkeringPlass valgt;

     private GridPane bestillingPane;
    private  Button[][] parkButtons;
    private Boolean [][] parkButtonsBool;
    private int wrapW = 180;


    private final double intialBlur = 4;
    private BoxBlur blur = new BoxBlur(intialBlur, intialBlur, 5);


    Stop[] stops = new Stop[] {
            new Stop(0, colors.clone()[1]),
            new Stop(1, colors.clone()[2])
    };
    LinearGradient linearGradient =
            new LinearGradient(0, 0, 1, 0, true, CycleMethod.REPEAT, stops);

    //velg parkeringsplass
    private ArrayList<RadioButton> buttons = new ArrayList<>();
    private ToggleGroup radioGroup;
    private RadioButton Tista, hiofR;

    public UserView(Stage stage){
        this.stage = stage;

        root = new StackPane();
        pane = new GridPane();
        container = new Pane();
        infoPane = new GridPane();
        choosePane = new GridPane();
        bestillingPane= new GridPane();


        infoPane = new GridPane();
        infoPane.setId("infoPane");
        infoPane.setAlignment(Pos.TOP_CENTER);
        infoPane.setMaxHeight(200);
        infoPane.setMaxWidth(400);
        infoPane.setMinWidth(200);

        infoPane.setTranslateX(-450);
        infoPane.setVgap(5);
        infoPane.setHgap(5);


        bestillingPane.setAlignment(Pos.TOP_CENTER);
        bestillingPane.setId("infoPane");

        bestillingPane.setMaxHeight(200);
        bestillingPane.setMaxWidth(300);
        bestillingPane.setTranslateX(500);
        bestillingPane.setVgap(5);
        bestillingPane.setHgap(5);







        choosePane = new GridPane();

        choosePane.setId("choosePane");
        choosePane.setAlignment(Pos.CENTER);
        choosePane.setHgap(10);
        choosePane.setMaxSize(600, 100);
        choosePane.toFront();


        container.setStyle("-fx-background-color:  white");
        pane.setMaxSize(600, 600);
        pane.setTranslateX(50);
        pane.setAlignment(Pos.TOP_CENTER);

        root.getChildren().add(container);
        root.getChildren().add(choosePane);
        root.getChildren().add(pane);

        root.getChildren().add(infoPane);
        root.getChildren().add(bestillingPane);


        choosePane.setVisible(true);
        infoPane.setVisible(false);
        bestillingPane.setVisible(false);
        pane.setVisible(false);

        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);

        initParkingplasser();
        initPaneBakgrunn();

        init();
    }

    public void initParkingplasser(){

        Haldensentrum = new Parkeringsplass("Halden Tista Sentrum", "Walkers gate 4, 1771 Halden", 24f, 25);
       // parkButtons= new Button[Haldensentrum.getPlasser() / 5][Haldensentrum.getPlasser() / 5];


        Text ParkeringsNavn = new Text("ParkeringsNavn: " + Haldensentrum.getParkeringnavn());
        ParkeringsNavn.setId("text");
        ParkeringsNavn.setWrappingWidth(wrapW);


        Text adresse = new Text("Adresse: " + Haldensentrum.getAdresse());
        adresse.setId("text");
        adresse.setWrappingWidth(wrapW);

        Text pris = new Text("Pris Per Time: " + Haldensentrum.getPris());
        pris.setId("text");
        pris.setWrappingWidth(wrapW);

        Text LedigePlasser = new Text("Antall Ledige Plasser: " + Haldensentrum.getPlasser());
        LedigePlasser.setId("text");
        LedigePlasser.setWrappingWidth(wrapW);


        hiof = new Parkeringsplass("Hogskolen i Ostfold", "B R A Veien 4, 1757 Halden", 30f, 50);

        Text ParkeringsNavn2 = new Text("ParkeringsNavn: " + hiof.getParkeringnavn());
        ParkeringsNavn2.setId("text");
        ParkeringsNavn2.setWrappingWidth(wrapW);

        Text adresse2 = new Text("Adresse: " + hiof.getAdresse());
        adresse2.setId("text");
        adresse2.setWrappingWidth(wrapW);

        Text pris2 = new Text("Pris Per Time: " + hiof.getPris());
        pris2.setId("text");
        pris2.setWrappingWidth(wrapW);

        Text LedigePlasser2 = new Text("Antall Ledige Plasser: " + hiof.getPlasser());
        LedigePlasser2.setId("text");
        LedigePlasser2.setWrappingWidth(wrapW);


        pane.setVgap(5);
        pane.setHgap(5);
        blur.setInput(new ColorAdjust(0,0,0.4,0));

        velgPark();
        confirmSelect.setOnAction(action ->{

            if(radioGroup.getSelectedToggle() == Tista){
                valgt = ValgtParkeringPlass.tista;
                infoPane.getChildren().clear();
                pane.getChildren().clear();

                infoPane.setVisible(true);
                pane.setVisible(true);
                choosePane.setVisible(false);
                bestillingPane.setVisible(false);

                infoPane.add(ParkeringsNavn, 0,0);
                infoPane.add(adresse, 0,1);
                infoPane.add(pris, 0,2);
                infoPane.add(LedigePlasser, 0,3);
                parkButtons = new Button[Haldensentrum.getPlasser()][Haldensentrum.getPlasser()];
                parkButtonsBool = new Boolean[Haldensentrum.getPlasser()][Haldensentrum.getPlasser()];

                for(int i=1; i <=Haldensentrum.getPlasser() / 5  ; i++ ){
                    for(int j=1; j <= Haldensentrum.getPlasser() / 5; j++){
                        int finalI = i;
                        int finalJ = j;
                        parkButtons[i][j] = new Button();
                        parkButtonsBool[i][j] = false;
                        parkButtons[i][j].setText("Bestill nr" + j * i ) ;
                        parkButtons[i][j].setPrefSize(200, 100);
                        parkButtons[i][j].setId("parkImg");
                        pane.add(parkButtons[i][j], j, i);

                        parkButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                parkButtonsBool[finalI][finalJ] = false;
                                bestillingPane.setVisible(true);
                                parkButtons[finalI][finalJ].setDisable(true);

                            }
                        });

                    }
                }

                Button goBack = new Button();
                goBack.setId("goback");
                goBack.setPrefSize(50,50);

                infoPane.add(goBack, 1,2);


                goBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        infoPane.setVisible(false);
                        bestillingPane.setVisible(false);
                        pane.setVisible(false);
                        choosePane.setVisible(true);
                        parkButtons = new Button[0][0];
                    }


                });


            }else if(radioGroup.getSelectedToggle() == hiofR ){
                valgt = ValgtParkeringPlass.hiof;

                infoPane.getChildren().clear();
                pane.getChildren().clear();
                infoPane.setVisible(true);
                pane.setVisible(true);

                bestillingPane.setVisible(false);
                choosePane.setVisible(false);

                infoPane.add(ParkeringsNavn2, 0,0);
                infoPane.add(adresse2, 1,0);
                infoPane.add(pris2, 0,1);
                infoPane.add(LedigePlasser2, 1,1);

              parkButtons = new Button[hiof.getPlasser()][hiof.getPlasser()];
                parkButtonsBool = new Boolean[hiof.getPlasser()][hiof.getPlasser()];

                for(int i=0; i <hiof.getPlasser() / 5 ; i++ ){
                    for(int j=0; j < hiof.getPlasser() / 5; j++){
                        int finalI = i;
                        int finalJ = j;
                        parkButtons[i][j] = new Button();
                        parkButtonsBool[i][j] = false;
                        parkButtons[i][j].setPrefSize(100, 100);


                        parkButtons[i][j].setId("parkImg");
                        pane.add(parkButtons[i][j], j, i);

                        parkButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                parkButtonsBool[finalI][finalJ] = false;
                                parkButtons[finalI][finalJ].setStyle("-fx-background-color: red");
                                bestillingPane.setVisible(true);
                                parkButtons[finalI][finalJ].setDisable(true);

                            }
                        });

                    }
                }
                Button goBack = new Button();
                goBack.setId("goback");
                goBack.setPrefSize(50,50);

                infoPane.add(goBack, 1,2);



                goBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        infoPane.setVisible(false);
                        bestillingPane.setVisible(false);
                        pane.setVisible(false);
                        choosePane.setVisible(true);
                        parkButtons = new Button[0][0];

                    }


                });
            }

        });







    }


    public void velgPark(){

        Text text = new Text("ParkeringSteder I Naerheten");
        text.setStyle("-fx-font-size: 30px; -fx-background-color: gray; -fx-border-color: black; -fx-border-width: 2px");
        text.setX(400);
        text.setY(200);
        text.toFront();

        Tista = new RadioButton(Haldensentrum.getParkeringnavn());

        hiofR = new RadioButton(hiof.getParkeringnavn());
        velgParkRadio.add(Tista);
        velgParkRadio.add(hiofR);


        HBox radioBox = new HBox();
        radioBox.getChildren().addAll(Tista, hiofR);

        radioGroup = new ToggleGroup();
        Tista.setToggleGroup(radioGroup);
        hiofR.setToggleGroup(radioGroup);
         confirmSelect = new Button("Confirm");

        ImageView TistaImg = new ImageView(new Image("https://tellusdmsmedia.newmindmedia.com/wsimgs/18983398_10156189404679307_1537015086_n_781050600.jpg"));
        TistaImg.setFitWidth(200);
        TistaImg.setFitHeight(200);
        TistaImg.setId("parkChooseImg");

        ImageView hiofImg = new ImageView(new Image("https://upload.wikimedia.org/wikipedia/commons/c/c0/Hogskolesenteretihalden.jpg"));
        hiofImg.setFitWidth(200);
        hiofImg.setFitHeight(200);
        hiofImg.setId("parkChooseImg");

        choosePane.setPadding(new Insets(20,20,20,20));


        container.getChildren().add(text);
        choosePane.add(TistaImg, 0,1);
        choosePane.add(hiofImg, 1,1);

        choosePane.add(Tista, 0,2);
        choosePane.add(hiofR, 1,2);

        choosePane.add(confirmSelect, 0,3);

    }



    public void init(){
        pane.setAlignment(Pos.CENTER);
        pane.setId("viewPane");





        scene.getStylesheets().add("style.css");


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



        TranslateTransition  transition= new TranslateTransition();
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
    public GridPane getPane (){return pane;}
}
