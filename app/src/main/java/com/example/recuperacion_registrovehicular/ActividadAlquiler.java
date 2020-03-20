package com.example.recuperacion_registrovehicular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recuperacion_registrovehicular.Clases.Horario;
import com.example.recuperacion_registrovehicular.Volley.Cartelera_SingletonVolley;
import com.example.recuperacion_registrovehicular.Volley.ServicioWebvolleyCartelera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActividadAlquiler extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    EditText nro, HR;
    List<Horario> listaC;
    Button registrarA;
    Horario_Adapter adapter;
    ServicioWebvolleyCartelera sw = new ServicioWebvolleyCartelera(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_alquiler);
        cargarComponentes();
        cargarLista(sw.getAll());
    }

    public void cargarComponentes(){
        recyclerView = findViewById(R.id.recycler);
        nro = findViewById(R.id.txtnroRegistro);
        HR = findViewById(R.id.txthoraRecogida);
        registrarA = findViewById(R.id.btnregistrarAlquiler);
        registrarA.setOnClickListener(this);
    }
    public void cargarLista(List<Horario> lista){
        listaC = new ArrayList<Horario>();
        listaC = lista;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Horario_Adapter(listaC);
        adapter.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Horario horario = listaC.get(recyclerView.getChildAdapterPosition(v));
                nro.setText(horario.getNro_registro() + "");
            }
        });


        recyclerView.setAdapter(adapter);
    }
    public void registrarAlquilera(final String codigo){
        Log.e("response","metodo");
        String path = "http://192.168.0.109:8080/usuario/alquilar" + codigo ;;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("response","response" + response);

                if(response.trim().equalsIgnoreCase("registrado")){

                    Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_SHORT).show();
                    nro.setText("");
                    HR.setText("");




                }else{
                    Toast.makeText(getApplicationContext(), "Debes seleccionar un vehiculo", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Debes seleccionar un vehiculo", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("hora_recogida", HR.getText().toString().trim());
                params.put("horario", nro.getText().toString().trim());

                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Cartelera_SingletonVolley.getInstance(this).addRquestque(stringRequest);

    }

    @Override
    public void onClick(View v) {
        Bundle datos = this.getIntent().getExtras();
        String verN = datos.getString("cedula");
        registrarAlquilera("/"+ verN);

    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu );
        return true;
    }


    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.idcerrarSesion){
            Intent intent1 = new Intent(ActividadAlquiler.this,MainActivity.class);

            startActivity(intent1);
        }
        return true;

    }
}
