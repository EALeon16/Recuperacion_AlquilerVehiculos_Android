package com.example.recuperacion_registrovehicular.Volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.recuperacion_registrovehicular.Clases.Horario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicioWebvolleyCartelera {

    Context context;



    String consulta = "";
    boolean estado;
    final public List<Horario> lista;


    public ServicioWebvolleyCartelera(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    public List<Horario> getAll() {
        String url = "http://192.168.0.109:8080/alquilerVehiculo/getAll";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // devuelve un json
                try {
                    Log.e("entro al catch", "entro al catch");
                    //Toast.makeText(context,  "estado: " + estado, Toast.LENGTH_SHORT).show();
                    JSONArray jsonUsuario = response.getJSONArray("data");
                    Log.e("jsonUsuario", "jsonUsuario" + jsonUsuario);
                    for (int i = 0; i < jsonUsuario.length(); i++) {
                        JSONObject jsonObject = jsonUsuario.getJSONObject(i);
                        //Toast.makeText(context, jsonObject.toString(), Toast.LENGTH_LONG  ).show();
                        Horario horario = new Horario();
                        horario.setFecha_recogida(jsonObject.getString("fecha_recogida"));
                        horario.setFecha_devolucion(jsonObject.getString("fecha_devolucion"));
                        horario.setDias(jsonObject.getInt("dias"));
                        horario.setNro_registro(jsonObject.getInt("horarioA_id"));
                        horario.setPrecio_total(jsonObject.getDouble("precio_total"));
                        horario.setPlaca(jsonObject.getString("placa"));
                        horario.setMarca(jsonObject.getString("marca"));
                        horario.setModelo(jsonObject.getString("modelo"));
                        horario.setTipo_vehiculo(jsonObject.getString("tipo_vehiculo"));

                        lista.add(horario);
                        Log.e("Lista", lista.size()+"");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("ERRORT", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRORR", error.getMessage());

            }
        }
        );
        Cartelera_SingletonVolley.getInstance(context).addRquestque(jsonObjectRequest);
        Log.e("Lista", lista.size()+"");
        return lista;
    }

}

