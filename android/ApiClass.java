package com.example.myapplication;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {

    private JsonPlaceHolderApi json;

    public ApiClass() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://mapserverapi.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        json = retrofit.create(JsonPlaceHolderApi.class);
    }

    public void sendEntity(Entity entity) {
        String jsonStr = new Gson().toJson(entity.getCoords());
        System.out.println("\n\n\n\n\n\n!!!!!!!!!!!!!!!!!=");
        System.out.println(jsonStr);
        entity.setTest(jsonStr);
        System.out.println("=!!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n\n\n");


        Call<Entity> call = json.createActivity(entity);

        call.enqueue(new Callback<Entity>() {
            @Override
            public void onResponse(Call<Entity> call, Response<Entity> response) {
                if (!response.isSuccessful()){
                    System.out.println("\n\n!!!!!!!!!onResponse3===== " + "Code:" +
                            response.code() +
                            "\nError: " + response.body() + "\n" +
                            "=====!!!!!!!!!\n\n");
                    return;
                }
                Entity entityResponse = response.body();

                System.out.println("\n\n!!!!!!!!!onResponse4===== " +
                        "Code: " + response.code() + "\n" +
                        "\nError: " + response.errorBody() + "\n" +
                        "\nError: " + response.message() + "\n" +
                        "\nError: " + response.toString() + "\n" +
                        "=====!!!!!!!!!\n\n");
            }

            @Override
            public void onFailure(Call<Entity> call, Throwable t) {
                System.out.println("\n\n!!!!!!!!!onFailure===== " +
                        t.getMessage() +
                        "=====!!!!!!!!!\n\n");
            }
        });
    }

    public JsonPlaceHolderApi getJson() {
        return json;
    }

    public void setJson(JsonPlaceHolderApi json) {
        this.json = json;
    }
}
