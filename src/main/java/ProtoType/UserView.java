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
import Parkeringsplass.Bestilling;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import kotlin.reflect.KCallable;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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

    private ArrayList<Bestilling> bestillinger = new ArrayList<>();


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
    private Button valgtbtn;
    public void initParkeringsplasser() {
        repo = new JSONRepo();

        bestillinger = repo.LoadFileBestillinger("bestillinger.json");
        for (int x = 0; x < vp.getButtons().size(); x++) {
            if (vp.getRadioGroup().getSelectedToggle() == vp.getButtons().get(x)) {

                infoPane.getChildren().clear();
                buttonspane.getChildren().clear();


                infoPane.add(vp.getParkeringsnavner().get(x), 0, 0);
                infoPane.add(vp.getAdresser().get(x), 0, 1);
                infoPane.add(vp.getPriser().get(x), 0, 2);
                infoPane.add(vp.getLedigplasser().get(x), 0, 3);
                parkButtons = new Button[vp.getParkeringsplasser().get(x).getPlasser() / (int) Math.sqrt(vp.getParkeringsplasser().get(x).getPlasser())][vp.getParkeringsplasser().get(x).getPlasser() / (int) Math.sqrt(vp.getParkeringsplasser().get(x).getPlasser())];
                parkButtonsBool = new Boolean[vp.getParkeringsplasser().get(x).getPlasser() / (int) Math.sqrt(vp.getParkeringsplasser().get(x).getPlasser())][vp.getParkeringsplasser().get(x).getPlasser() / (int) Math.sqrt(vp.getParkeringsplasser().get(x).getPlasser())];

                for (int i = 0; i < parkButtons.length; i++) {
                    for (int j = 0; j < parkButtons.length; j++) {
                        parkButtons[i][j] = new Button();
                        parkButtonsBool[i][j] = false;
                        count++;
                        parkButtons[i][j].setText(String.valueOf(count));
                        parkButtons[i][j].setId("parkImg");

                        parkButtons[i][j].setPrefSize(200, 100);
                        //     System.out.println("\n" + bestillinger.size());

                        int finalI = i;
                        int finalJ = j;
                        int finalX = x;
                        int finalX1 = x;

                            for (int ii = 0; ii < bestillinger.size(); ii++) {

                                if (bestillinger.get(ii).getRutenr() == count && (  vp.getParkeringsplasser().get(x).getParkeringnavn().equals(bestillinger.get(ii).getParkeringsplassnavn()) )) {
                                    parkButtons[i][j].setId("parkimg2");
                                    String rutnr = parkButtons[finalI][finalJ].getText();

                                    parkButtons[i][j].setDisable(true);
                                    DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
                                    Calendar calendar =   GregorianCalendar.getInstance();
                                    calendar.set(calendar.get(Calendar.YEAR),  calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_YEAR), bestillinger.get(ii).getTilTime(),
                                            bestillinger.get(ii).getTilMinut()  );
                                     Timeline timeline = new Timeline();
                                    timeline.getKeyFrames().add(   new KeyFrame(
                                            Duration.millis( 500 ),
                                            event -> {
                                                final long diff =  System.currentTimeMillis();
                                                if ( diff < 0 ) {
                                                    parkButtons[finalI][finalJ].setText( timeFormat.format( 0 ) );

                                                } else {
                                                    parkButtons[finalI][finalJ].setText( timeFormat.format(    calendar.getTimeInMillis()  - diff   ));
                                                 /*   System.out.println("millis: "  + (calendar.getTimeInMillis()  - diff) + "Time : " +
                                                            timeFormat.format(    calendar.getTimeInMillis()  - diff   ));
                                                   */
                                                    if( timeFormat.format(    calendar.getTimeInMillis()  - diff   ).equals("00:00:00")) {
                                                        bestillinger =  repo.deleteBestilling(Integer.valueOf(rutnr));
                                                        System.out.print("test: " + rutnr);
                                                        repo.WriteToJSONBestilling("bestillinger.json", bestillinger);

                                                        parkButtons[finalI][finalI].setId("parkImg");
                                                        parkButtons[finalI][finalI].setText(rutnr);
                                                        timeline.stop();



                                                    }else{

                                                    }

                                                }
                                            }
                                    ));



                                    timeline.setCycleCount( Animation.INDEFINITE );
                                    timeline.play();

                                }
                            }




                        parkButtons[i][j].setOnAction(action -> {

                            BestillingView bv = new BestillingView(stage, vp, new Text(vp.getParkeringsplasser().get(finalX).getParkeringnavn()), new Text(parkButtons[finalI][finalJ].getText()), new Text(String.valueOf(vp.getParkeringsplasser().get(finalX).getPris())) );
                            bv.getConfirm().setOnAction(action2 ->{
                                parkButtonsBool[finalI][finalJ] = true;

                               bestillinger.add(new Bestilling(LaunchProtoType.loggedon,   Integer.valueOf( parkButtons[finalI][finalJ].getText()),vp.getParkeringsplasser().get(finalX).getParkeringnavn(), bv.getNavn().getText(), bv.getTlf().getText(), bv.getSpinner().getValue(),  bv.getSpinner2().getValue(),
                                        bv.getSpinner3().getValue(), bv.getSpinner4().getValue()));
                                parkButtons[finalI][finalJ].setDisable(true);
                                repo.WriteToJSONBestilling("bestillinger.json", bestillinger);

                                stage.setScene(scene);
                            });
                        });

                        buttonspane.add(parkButtons[i][j], j, i);


                    }


                }
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
                VelgParkeringsPlass velgParkeringsPlass = new VelgParkeringsPlass(stage);
            }


        });
    }
    int count = 0;



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
