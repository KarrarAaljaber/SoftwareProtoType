package ProtoType;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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





    public BestillingView(Text parkeringsnavn, Text  rute){
        this.parkeringsnavn = parkeringsnavn;
        this.rute = rute;
        root = new StackPane();
        bestillingPane= new GridPane();
        bestillingPane.setId("bestilling");

        bestillingPane.setMaxHeight(500);
        bestillingPane.setMaxWidth(500);




        root.getChildren().add(bestillingPane);
        root.setStyle("-fx-background-color: rgba(22,22,22,1);");

        scene = new Scene(root, 1280 , 720);
        scene.getStylesheets().add("style.css");


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


        bestillingPane.setGridLinesVisible(true);
        bestillingPane.setAlignment(Pos.CENTER);


    }

    public Scene getScene(){
        return scene;
    }






}
