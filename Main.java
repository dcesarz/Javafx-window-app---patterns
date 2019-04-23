
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main extends Application{


    @Override
    public void start(Stage stage) {

        stage.initStyle(StageStyle.UNDECORATED);
        GridPane window = new GridPane();

        HBox panel_u = new HBox();
        panel_u.setId("u");
        panel_u.setMinWidth(200);
        panel_u.setAlignment(Pos.TOP_RIGHT);
        panel_u.setPadding(new Insets(25, 25, 25, 25));
        panel_u.setSpacing(10);

       final Boolean flag = true;

        Button zwin = new Button("zwin do paska");
        zwin.setAlignment(Pos.TOP_RIGHT);
        Button zamknij = new Button("zamknij");
        zamknij.setAlignment(Pos.TOP_RIGHT);
        zamknij.setId("close");

        panel_u.getChildren().addAll(zwin,zamknij);
        Button dalej = new Button("dalej");


        VBox panel_dl = new VBox();
        panel_dl.setSpacing(18);
        panel_dl.setAlignment(Pos.BASELINE_RIGHT);
        panel_dl.setPadding(new Insets(30, 25, 25, 25));
        panel_dl.setId("dl");
        panel_dl.setMaxWidth(300);
        panel_dl.setMinWidth(300);

        VBox panel_dr = new VBox();
        panel_dr.setSpacing(10);
        panel_dr.setAlignment(Pos.BASELINE_LEFT);
        panel_dr.setPadding(new Insets(25, 25, 25, 25));
        panel_dr.setId("dr");
        panel_dr.setMaxWidth(250);
        panel_dr.setMinWidth(250);

        VBox panel_drr = new VBox();
        panel_drr.setSpacing(18);
        panel_drr.setAlignment(Pos.BASELINE_LEFT);
        panel_drr.setPadding(new Insets(25, 25, 25, 0));
        panel_drr.setId("drr");
        panel_drr.setMaxWidth(200);
        panel_drr.setMinWidth(200);

        window.add(panel_u,10,0,1,1);
        window.add(panel_dl, 3, 1, 2, 10);
        window.add(panel_dr, 5, 1, 3, 10);
        window.add(panel_drr, 9, 1, 2, 1);

        Label result, e1, e2;

        Label labpes = new Label("Pesel");
        labpes.minHeight(42);
        Label labmiasto = new Label("Miasto");
        labmiasto.minHeight(42);
        Label labkp = new Label("Kod pocztowy");
        labkp.minHeight(42);
        Label labtel = new Label("Telefon kontaktowy");
        labtel.minHeight(42);
        Label labemail = new Label("E-mail");
        labemail.minHeight(42);
        Label labtelpattern = new Label("(+48)-xxx-xxx-xxx");
        labtelpattern.setPadding(new Insets(111,0,0,0));

        panel_dl.getChildren().addAll(labpes,labmiasto,labkp,labtel,labemail);

        TextField peseltxtf = new TextField();
        peseltxtf.setPrefColumnCount(2);
        TextField miastotxtf = new TextField();
        miastotxtf.setPrefColumnCount(2);
        TextField kptxtf = new TextField();
        kptxtf.setMaxWidth(100);
        TextField teltxtf = new TextField();
        teltxtf.setMaxWidth(100);
        TextField emailtxtf = new TextField();
        emailtxtf.setPrefColumnCount(2);
        panel_dr.getChildren().addAll(peseltxtf,miastotxtf,kptxtf,teltxtf,emailtxtf,dalej);
        panel_drr.getChildren().addAll(labtelpattern);
        zamknij.setOnAction(a->
                {

                    stage.close();

                }
        );
        zwin.setOnAction(b->
        {

            stage.setIconified(true);

        });

        dalej.setOnAction(c->
                {

             if(flag) {
                 Pattern peselpatt = Pattern.compile("[0-9]{11}");
                 Pattern miastopatt = Pattern.compile("([A-B][a-b])*");
                 Pattern kodpatt = Pattern.compile("[0-9]{2}-[0-9]{3}");
                 Pattern telpatt = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{3}");
                 Pattern emailpatt = Pattern.compile("[a-b]*@[a-b][.][a-b]");

                 panel_dr.setSpacing(18);
                 String pesel = new String(peseltxtf.getText());
                 String miasto = new String(miastotxtf.getText());
                 String kod = new String(kptxtf.getText());
                 String tel = new String(teltxtf.getText());
                 String email = new String(emailtxtf.getText());
                 panel_dr.getChildren().removeAll(peseltxtf, miastotxtf, kptxtf, teltxtf, emailtxtf, dalej);
                 panel_drr.getChildren().removeAll(labtelpattern);

                 Label filler1 = new Label("**********");
                 Label lablad1 = new Label("Blad");
                 Label labcorr1 = new Label("Dane poprawne");
                 Label labempty1 = new Label("Nie podano");

                 if (!pesel.isEmpty()) {
                     Matcher test = peselpatt.matcher(pesel);
                     panel_dr.getChildren().add(filler1);
                     if (test.matches()) {
                         panel_drr.getChildren().add(labcorr1);
                     } else {
                         panel_drr.getChildren().add(lablad1);
                     }
                 } else {
                     panel_dr.getChildren().add(filler1);
                     panel_drr.getChildren().add(labempty1);

                 }

                 Label filler2 = new Label("**********");
                 Label lablad2 = new Label("Blad");
                 Label labcorr2 = new Label("Dane poprawne");
                 Label labempty2 = new Label("Nie podano");

                 if (!miasto.isEmpty()) {
                     Matcher test = miastopatt.matcher(miasto);
                     panel_dr.getChildren().add(filler2);
                     if (test.matches()) {
                         panel_drr.getChildren().add(labcorr2);
                     } else {
                         panel_drr.getChildren().add(lablad2);
                     }
                 } else {
                     panel_dr.getChildren().add(filler2);
                     panel_drr.getChildren().add(labempty2);

                 }

                 Label filler3 = new Label("**********");
                 Label lablad3 = new Label("Blad");
                 Label labcorr3 = new Label("Dane poprawne");
                 Label labempty3 = new Label("Nie podano");

                 if (!kod.isEmpty()) {
                     Matcher test = kodpatt.matcher(kod);
                     panel_dr.getChildren().add(filler3);
                     if (test.matches()) {
                         panel_drr.getChildren().add(labcorr3);
                     } else {
                         panel_drr.getChildren().add(lablad3);
                     }
                 } else {
                     panel_dr.getChildren().add(filler3);
                     panel_drr.getChildren().add(labempty3);

                 }
                 Label filler4 = new Label("**********");
                 Label lablad4 = new Label("Blad");
                 Label labcorr4 = new Label("Dane poprawne");
                 Label labempty4 = new Label("Nie podano");

                 if (!tel.isEmpty()) {
                     Matcher test = telpatt.matcher(tel);
                     panel_dr.getChildren().add(filler4);
                     if (test.matches()) {
                         panel_drr.getChildren().add(labcorr4);
                     } else {
                         panel_drr.getChildren().add(lablad4);
                     }
                 } else {
                     panel_dr.getChildren().add(filler4);
                     panel_drr.getChildren().add(labempty4);

                 }

                 Label filler5 = new Label("**********");
                 Label lablad5 = new Label("Blad");
                 Label labcorr5 = new Label("Dane poprawne");
                 Label labempty5 = new Label("Nie podano");

                 if (!email.isEmpty()) {
                     Matcher test = emailpatt.matcher(email);
                     panel_dr.getChildren().add(filler5);
                     if (test.matches()) {
                         panel_drr.getChildren().add(labcorr5);
                     } else {
                         panel_drr.getChildren().add(lablad5);
                     }
                 } else {
                     panel_dr.getChildren().add(filler5);
                     panel_drr.getChildren().add(labempty5);

                 }

             }

                }
        );


        Scene sc = new Scene(window, 800, 400);

        stage.setTitle("TEST");
        stage.setScene(sc);
        stage.show();
    }
    public static void main(String[] args) {
        // write your code here
        launch(args);
    }
}
