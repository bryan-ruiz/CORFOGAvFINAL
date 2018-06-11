package com.example.bryan.corfoga.InternetConection;

import com.example.bryan.corfoga.Class.Animal;
import com.example.bryan.corfoga.Class.Farm;
import com.example.bryan.corfoga.Class.Inspection;
import com.example.bryan.corfoga.Class.InspectionVisitComment;
import com.example.bryan.corfoga.Class.User;

import com.example.bryan.corfoga.Class.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Conection {
    @GET("/api/login/{user}/{pass}")
    Call<User> getUser(@Path("user") String user, @Path("pass") String pass);
    @GET("/api/fincas/get/{region}")
    Call<List<Farm>> getFarmsFromRegion(@Path("region") int region);
    @GET("/api/animales/get/{farm}")
    Call<List<Animal>> getAnimalsFromFarm(@Path("farm") int farm);

    @GET("/api/inspecciones/getAnimalesvisita/{visita}/{idfinca}/{anno}")
    Call<List<InspectionVisitComment>> getAnimalsState(@Path("visita") String visita, @Path("idfinca") String idfinca, @Path("anno") String anno);

    @POST("/api/inspecciones/create")
    Call<String>addInspection(@Body Inspection inspection);
}