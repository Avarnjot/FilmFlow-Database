// Hi Professor Zahy
// Avarnjot Here,
//        I will provide a brief description of my assignment in which I made an application which uses GSON & JSON at the same time to provide us with information fetched
//        from an api of movies database. In which, I called an url(API) with the http request and in response received the information in JSON and to display that JSON
//        fetched information, I used GSON to parse the JSON string into Java objects. So, it was all about connecting with API and calling it, receiving a JSON response
//        converting the response into java objects(Like - private String Title) with the help of GSON and just displaying all this with cool javaFX.
//        Hope you will like it.


package com.example.assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

//    default method to load the fxml file
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Avarn's Movie Database");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
