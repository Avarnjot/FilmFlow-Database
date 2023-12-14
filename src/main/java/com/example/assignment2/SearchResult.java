package com.example.assignment2;

import java.util.List;

// This class 'SearchResult' represent the JSON response received from the API when searching for movies.

public class SearchResult {
    private List<Movie> Search;
    private String totalResults;
    private String Response;

    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
