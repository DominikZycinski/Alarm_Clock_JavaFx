package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Alert;



public class JavaFXDB extends Application  {

    Scene scene1;
    @Override
    public void start(Stage primaryStage) throws Exception{

        System.out.println(Global.time);
        Parent root = FXMLLoader.load(getClass().getResource("db.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("sample/Styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        int i = 0;
        int x = 0;
        TimerTask task = new TimerTask()
        {

            int seconds = 8;
            int i = 0;
            int x =0;
            File music = new File("D:\\Projekty\\IntelijjProject\\TableViewSqlJavaFX\\MoodyLoop.wav" +
                    "");
            @Override
            public void run()
            {
                int size = Global.timeTable.size();


                String s = String.valueOf(LocalTime.now());
                String time = s.substring(0,5) ;
                String time2 = s.substring(0,8) ;

                System.out.println(time2);

                System.out.println("time= "+time + "    " + Global.timeTable.get(i));
                if(time.equals(Global.timeTable.get(i))){
                    x++;
                    System.out.println("Budzik");

                    play("D:\\Projekty\\IntelijjProject\\TableViewSqlJavaFX\\analog-watch-alarm_daniel-simion.wav");
                    try {
//                        showAlert();
                        Thread.sleep((long)60000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                i++;

                if(i == size)
                {
                    i =0;
                }

            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);


        primaryStage.setOnCloseRequest(e->{

        });
    }


    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message Here...");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");
        alert.showAndWait();
    }


    public static void play(String filename)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();


        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
