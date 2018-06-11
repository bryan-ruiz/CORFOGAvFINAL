package com.example.bryan.corfoga.InternetConection;

import com.example.bryan.corfoga.Class.Animal;
import com.example.bryan.corfoga.Class.Farm;
import com.example.bryan.corfoga.Class.Global;
import com.example.bryan.corfoga.Class.Inspection;
import com.example.bryan.corfoga.Class.Region;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bryan on 05/06/2018.
 */

public class ServerConnection {
    private String ipAddress;
    private Retrofit query;
    private Conection conection;
    private static ServerConnection instance = null;

    public static ServerConnection getInstance() {
        if(instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }

    private ServerConnection() {
        this.ipAddress = "http://192.168.2.125:8000";
        this.query = new Retrofit.Builder()
                .baseUrl(this.ipAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.conection = query.create(Conection.class);
    }

    public Conection getConection() {
        return conection;
    }
}
