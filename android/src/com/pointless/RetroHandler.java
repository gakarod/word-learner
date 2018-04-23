package com.pointless;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroHandler implements UrlHandler {
    ExerciseData exercise;
    boolean exerciseDownloaded = false;

    @Override
    public void callServer() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://blackboardradio.kebwfmajiq.ap-south-1.elasticbeanstalk.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UrlService service = retrofit.create(UrlService.class);
        Call<ExerciseData> call = service.getJsonData("json");

        call.enqueue(new Callback<ExerciseData>() {
            @Override
            public void onResponse(Call<ExerciseData>call, Response<ExerciseData> response) {

                setExercise(response.body());

                Log.v("data gotten wooohohohh", exercise.getWords().toString());

                exerciseDownloaded = true;

            }

            @Override
            public void onFailure(Call<ExerciseData>call, Throwable t) {
                // Log error here since request failed
                Log.e("Retro error", t.toString());
            }
        });

    }

    @Override
    public void postServer() {

    }

    @Override
    public ExerciseData getExercise() {
        if (exerciseDownloaded) return exercise;
        else {
           // System.exit(0);
        return null;

    }
    }

    public void setExercise(ExerciseData object){

        exercise = new ExerciseData(object);
//        exercise.image_urls = object.image_urls;
        Log.v("instance", "yay");
        Log.v("object", exercise.toString());


    }
}
