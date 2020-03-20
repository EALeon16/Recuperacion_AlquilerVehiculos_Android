package com.example.recuperacion_registrovehicular.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Cartelera_SingletonVolley {

    private RequestQueue queue;
    private Context context;
    private static Cartelera_SingletonVolley miInstancia;

    public Cartelera_SingletonVolley(Context context) {
        this.context = context;
        ////get RequestQueUqe
        queue = getRequestQue();

    }


    public RequestQueue getRequestQue(){

        if(queue == null)
            queue = Volley.newRequestQueue(context.getApplicationContext());
            return queue;


    }

    public static synchronized Cartelera_SingletonVolley getInstance(Context context){
        if(miInstancia == null){
            miInstancia = new Cartelera_SingletonVolley(context);
        }
        return miInstancia;
    }


    public <T> void addRquestque(Request request){
        queue.add(request);


    }
}
