package com.epicodus.moviedatabase;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {

    public static void getMovie(String title, String searchType, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addPathSegment(searchType);
        urlBuilder.addQueryParameter("query", title);
        urlBuilder.addQueryParameter("api_key", Constants.MOVIE_API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response){
        ArrayList<Movie> movies = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject resultsJSON = new JSONObject(jsonData);
                JSONArray moviesJSON = resultsJSON.getJSONArray("results");
                for(int i=0; i < moviesJSON.length(); i++){
                    JSONObject movieJSON = moviesJSON.getJSONObject(i);
                    String title = movieJSON.getString("title");
                    String year = movieJSON.getString("release_date");
                    int id = movieJSON.getInt("id");
                    String genre = movieJSON.getJSONArray("genre_ids").getString(0);
                    double rating = movieJSON.getDouble("vote_average");
                    String overview = movieJSON.getString("overview");
                    String imageURL = movieJSON.getString("poster_path");
                    DateFormat formatter = new SimpleDateFormat("y");
                    String dateString = "";
                    try{
                        Date date = formatter.parse(year);
                        dateString = formatter.format(date);
                    } catch (Exception e){
                        Log.d("no", "no");
                    }
                    Log.d("log", dateString);
                    Movie movie = new Movie(id,title,overview, "cast", imageURL, genre,rating, dateString);
                    movies.add(movie);


                }
            }
        }

        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        } return movies;
    }
}
