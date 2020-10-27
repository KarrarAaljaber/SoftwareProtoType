package ProtoType;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BestillingView {
    private Scene scene;
    private GridPane bestillingPane;
    private StackPane root;


    private Text parkeringsnavn;
    private Text rute;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();



    private Text localTime;



    private Label navnL;
    private TextField navn;
    private Label tlfL;
    private TextField tlf;

    private TextField bilskiltnr;
    private Label bilskiltnrL;




    private Stage stage;
    private VelgParkeringsPlass vp;
    public BestillingView(Stage stage, VelgParkeringsPlass vp, Text parkeringsnavn, Text  rute){
        this.parkeringsnavn = parkeringsnavn;
        this.vp = vp;
        this.rute = rute;
        this.stage = stage;
        root = new StackPane();
        bestillingPane= new GridPane();
        bestillingPane.setId("bestilling");

        bestillingPane.setMaxHeight(600);
        bestillingPane.setMaxWidth(600);




        root.getChildren().add(bestillingPane);
        root.setStyle("-fx-background-color: rgba(22,22,22,1);");

        scene = new Scene(root, 1280 , 720);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

        init(parkeringsnavn, rute);
    }

    public void init(Text parkeringsnavn,Text rute ){
        navnL = new Label("Full Navn: ");
        navn = new TextField();
        tlfL = new Label("Tlf nr: ");
        tlf = new TextField();

        bilskiltnrL = new Label("BilSkilt nr: ");
        bilskiltnr = new TextField();



        Label timeLabel = new Label();
        timeLabel.setId("time");
        DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis( 500 ),
                        event -> {
                            final long diff =  System.currentTimeMillis();
                            if ( diff < 0 ) {
                                timeLabel.setText( timeFormat.format( 0 ) );

                            } else {
                                timeLabel.setText( timeFormat.format( diff ) );
                            }
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();

        localTime = new Text();

        localTime.setText(timeline.toString());

        parkeringsnavn.setId("pkBestilling");
        parkeringsnavn.setWrappingWidth(0);
        bestillingPane.add(parkeringsnavn, 1, 0);
        bestillingPane.add(timeLabel, 0, 0);
        bestillingPane.add(rute, 0, 1);
        bestillingPane.add(navnL, 0,2);
        bestillingPane.add(navn, 1,2);
        bestillingPane.add(tlfL, 0,3);
        bestillingPane.add(tlf, 1,3);
        bestillingPane.add(bilskiltnrL, 0,4);
        bestillingPane.add(bilskiltnr, 1, 4);


      //  bestillingPane.setGridLinesVisible(true);
     //   bestillingPane.setAlignment(Pos.CENTER);
        bestillingPane.setMinWidth(500);
        bestillingPane.setMinHeight(500);
        bestillingPane.setHgap(20);
        bestillingPane.setVgap(20);


        Label hourL = new Label("Time: ");
        final Spinner<Integer> spinner = new Spinner<Integer>();

        Label minuteL = new Label("minutt: ");
        final Spinner<Integer> spinner2 = new Spinner<Integer>();


        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        SpinnerValueFactory<Integer> hour = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(  calendar.get(Calendar.HOUR_OF_DAY), 24,   calendar.get(Calendar.HOUR_OF_DAY));
        SpinnerValueFactory<Integer> minut = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(  calendar.get(Calendar.MINUTE), 60,   calendar.get(Calendar.MINUTE));
        spinner.setValueFactory(hour);
        spinner2.setValueFactory(minut);

        HBox tidbox= new HBox();
        Label fra = new Label("Fra: ");

        tidbox.getChildren().add(fra);
        tidbox.getChildren().add(hourL);
        tidbox.getChildren().add(spinner);
        tidbox.getChildren().add(minuteL);
        tidbox.getChildren().add(spinner2);


        Label hourL2 = new Label("Time: ");
        final Spinner<Integer> spinner3 = new Spinner<Integer>();

        Label minuteL2 = new Label("minutt: ");
        final Spinner<Integer> spinner4 = new Spinner<Integer>();
        SpinnerValueFactory<Integer> hour2 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(  calendar.get(Calendar.HOUR_OF_DAY), 24,   calendar.get(Calendar.HOUR_OF_DAY));
        SpinnerValueFactory<Integer> minut2 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(  calendar.get(Calendar.MINUTE), 60,   calendar.get(Calendar.MINUTE));
        spinner3.setValueFactory(hour2);
        spinner4.setValueFactory(minut2);

        HBox tidbox2= new HBox();
        Label til = new Label("Til: ");
        tidbox2.getChildren().add(til);
        tidbox2.getChildren().add(hourL2);
        tidbox2.getChildren().add(spinner3);
        tidbox2.getChildren().add(minuteL2);
        tidbox2.getChildren().add(spinner4);



        bestillingPane.add(tidbox, 0, 5, 2, 1);
        bestillingPane.add(tidbox2, 0, 6, 2, 1);

        bestillingPane.setVgap(10);

        Button confirm = new Button("bekreft betalling");
        bestillingPane.add(confirm, 0, 7, 2,1);

        Button goBack = new Button();
        goBack.setId("goback");
        goBack.setPrefSize(50, 50);

        goBack.setOnAction(action ->{UserView userView = new UserView(stage, vp); userView.visParkeringsplass();});

        bestillingPane.add(goBack, 2, 8, 2,1);


    }

    public Scene getScene(){
        return scene;
    }






}
