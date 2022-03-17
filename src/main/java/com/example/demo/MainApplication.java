package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Sequence-o-matic");
        stage.setScene(scene);
        AudioClip startupSound = new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("startup.wav")).toString());
        startupSound.play(200);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}