package com.example.wallpaperappassignment4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static Retrofit retrofit = null;
    public static final String API ="Vyzk8YFhn9xPZ3E7C44EcxHF2gxYg4fNLDfRFCnhsfEzzFRjyApytj4q";

    public static APIInterface getAPIInterface(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(APIInterface.class);
    }

}
