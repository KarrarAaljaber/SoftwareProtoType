package ProtoType;

import Parkeringsplass.Parkeringsplass;
import Repo.JSONRepo;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;


public class UserView {

    private Scene scene;
    private GridPane buttonspane;

    private Stage stage;

    private Pane backgroundContainer;

    private StackPane root;

    private GridPane infoPane;



    Color[] colors = {
            new Color(0.0, 0.9, 0.0, 1.0).saturate().brighter(),
            new Color(0.9, 0.0, 0.0, 1.0).saturate().brighter(),
            new Color(0.5, 0.5, 0.0, 1.0).saturate().brighter(),
            new Color(0.5, 0.9, 0.0, 1.0).saturate().brighter(),
            new Color(0.9, 0.5, 0.0, 1.0).saturate().brighter()
    };



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



    private VelgParkeringsPlass vp;
    public UserView(Stage stage, VelgParkeringsPlass vp){
        this.vp = vp;
        this.stage = stage;

        root = new StackPane();
        buttonspane = new GridPane();
        backgroundContainer = new Pane();
        infoPane = new GridPane();

        infoPane = new GridPane();
        infoPane.setId("infoPane");
        infoPane.setMaxHeight(200);
        infoPane.setMaxWidth(350);

        infoPane.setVgap(5);
        infoPane.setHgap(5);



        backgroundContainer.setStyle("-fx-background-color:  white");
        buttonspane.setMaxSize(600, 600);
        buttonspane.setTranslateX(50);

        root.getChildren().add(backgroundContainer);
        root.getChildren().add(buttonspane);
        root.getChildren().add(infoPane);
        root.setAlignment(buttonspane, Pos.CENTER);
        root.setAlignment(infoPane, Pos.CENTER_LEFT);
        backgroundContainer.setStyle("-fx-background-color: rgba(22,22,22,1);");

        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        buttonspane.setId("viewPane");
        scene.getStylesheets().add("style.css");
        initPaneBakgrunn();

    }

    private JSONRepo repo;
    public void visParkeringsplass(){

            for (int x = 0; x < vp.getButtons().size(); x++) {
                if (vp.getRadioGroup().getSelectedToggle() == vp.getButtons().get(x)) {

                    infoPane.getChildren().clear();
                    buttonspane.getChildren().clear();



                    infoPane.add(vp.getParkeringsnavner().get(x), 0, 0);
                    infoPane.add(vp.getAdresser().get(x), 0, 1);
                    infoPane.add(vp.getPriser().get(x), 0, 2);
                    infoPane.add(vp.getLedigplasser().get(x), 0, 3);
                    parkButtons = new Button[vp.getParkeringsplasser().get(x).getPlasser()][vp.getParkeringsplasser().get(x).getPlasser()];
                    parkButtonsBool = new Boolean[vp.getParkeringsplasser().get(x).getPlasser()][vp.getParkeringsplasser().get(x).getPlasser()];
                    int count = 0;
                    for (int i = 1; i <= vp.getParkeringsplasser().get(x).getPlasser() / Math.sqrt(vp.getParkeringsplasser().get(x).getPlasser()); i++) {
                        for (int j = 1; j <= vp.getParkeringsplasser().get(x).getPlasser()/ Math.sqrt(vp.getParkeringsplasser().get(x).getPlasser()); j++) {
                            int finalI = i;
                            int finalJ = j;
                            count++;
                            parkButtons[i][j] = new Button();
                            parkButtonsBool[i][j] = false;
                            parkButtons[i][j].setText( String.valueOf(count ));
                            parkButtons[i][j].setTextFill(Color.WHITE);
                            parkButtons[i][j].setFont(new Font("Arial", 16));
                            parkButtons[i][j].setPrefSize(200, 100);
                            parkButtons[i][j].setId("parkImg");
                            buttonspane.add(parkButtons[i][j], j , i);

                            int finalX = x;
                            parkButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    //parkButtonsBool[finalI][finalJ] = false;
                               //     bestillingPane.setVisible(true);

                                    //parkButtons[finalI][finalJ].setDisable(true);
                                BestillingView bv = new BestillingView(stage, vp, vp.getParkeringsnavner().get(finalX), new Text(parkButtons[finalI][finalJ].getText()), new Text(String.valueOf(vp.getParkeringsplasser().get(finalX).getPris())));


                                }
                            });

                        }
                    }

                    Button goBack = new Button();
                    goBack.setId("goback");
                    goBack.setPrefSize(50, 50);

                    infoPane.add(goBack, 1, 2);


                    goBack.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            /*
                            infoPane.setVisible(false);
                            pane.setVisible(false);
                            choosePane.setVisible(true);
                            */
                           // parkButtons = new Button[0][0];
                            VelgParkeringsPlass velgParkeringsPlass = new VelgParkeringsPlass(stage);



                        }


                    });

                }
            }




    }


    public void initPaneBakgrunn(){
        int spawnNodes = 25;
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
        backgroundContainer.getChildren().add(node);

    }


    public Scene getScene(){return scene;}
    public GridPane getPane (){return buttonspane;}
}
