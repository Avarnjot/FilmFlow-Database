package com.example.assignment2;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.net.URLEncoder;

//  This 'User' class interact with the API and fetch movies based on the searched/input movie title. It makes a HTTP requests to
//  the API and parsing the JSON response into of Movie objects with help of GSON.

public class User {

    private static final String url = "http://www.omdbapi.com/?apikey=e74af6db";
    private HttpClient httpClient;
    private Gson gson;

    public User() {
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public CompletableFuture<List<Movie>> getMovies(String movieName) throws UnsupportedEncodingException {
        // Inputted the movie title to be used as a parameter
        String encodedTitle = URLEncoder.encode(movieName, "UTF-8");

        // Append the inputted movie title to the URL to get JSON result
        String apiUrl = url + "&s=" + encodedTitle;

        // HTTP request
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(apiUrl))
                .GET()
                .build();

        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::parse);
    }

    private List<Movie> parse(String jsonString) {
        SearchResult searchResult = new Gson().fromJson(jsonString, SearchResult.class);

        // Checking if the Search in the JSON response is null
        if (searchResult.getSearch() == null) {
            return Collections.emptyList(); // Returning an empty list
        }

        List<Movie> movies = searchResult.getSearch();
        return movies;
    }
}





