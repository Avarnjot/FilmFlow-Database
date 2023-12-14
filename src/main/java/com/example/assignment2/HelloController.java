package com.example.assignment2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;


public class HelloController implements Initializable {

    @FXML
    private TextField movieNameTextField;

    @FXML
    private ListView<String> movieList;

    private User user;
    private List<Movie> movies; // Store the list of movies

    public HelloController() {
        user = new User();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set mouse click(event listener) on the ListView items
        movieList.setOnMouseClicked(event -> {
            optionClick();
        });
    }

    // when the user clicks 'Search' button this method invokes
    @FXML
    private void search() throws UnsupportedEncodingException {
        String movieName = movieNameTextField.getText();
        CompletableFuture<List<Movie>> future = user.getMovies(movieName);
        future.thenAccept(movies -> {
            this.movies = movies; // Store the list of movies
            ObservableList<String> movieTitles = FXCollections.observableArrayList();
            for (Movie movie : movies) {
                movieTitles.add(movie.getTitle());
            }
            Platform.runLater(() -> movieList.setItems(movieTitles));
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }

    // when user clicks any of the option which is being displayed after the search
    private void optionClick() {
        String selectedMovieTitle = movieList.getSelectionModel().getSelectedItem();
        if (selectedMovieTitle != null) {
            Movie selectedMovie = movieByTitle(selectedMovieTitle);
            if (selectedMovie != null) {
                displayMovieDetails(selectedMovie);
            }
        }
    }

    //  find and return a Movie from the list of movies based on the given movie title
    private Movie movieByTitle(String title) {
        for (Movie movie : movies) {
            if (title.equals(movie.getTitle())) {
                return movie;
            }
        }
        return null;
    }

    // displays an information message (Alert) containing the details of a selected movie.
    private void displayMovieDetails(Movie movie) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Movie Details");
        alert.setHeaderText(movie.getTitle());
        alert.setContentText("Year: " + movie.getYear() + "\n" +
                "IMDb ID: " + movie.getImdbID() + "\n" +
                "Type: " + movie.getType());

        alert.showAndWait();
    }
}

