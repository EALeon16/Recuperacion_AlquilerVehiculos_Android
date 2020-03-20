package com.example.recuperacion_registrovehicular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.recuperacion_registrovehicular.Volley.Cartelera_SingletonVolley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button registrar, ingresar;
    EditText cedula, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarComponentes();
    }

    public void cargarComponentes(){

        registrar = findViewById(R.id.btnRegistrase);
        registrar.setOnClickListener(this);
        ingresar = findViewById(R.id.btnIngresar);
        ingresar.setOnClickListener(this);
        correo = findViewById(R.id.txtUsuario);
        cedula = findViewById(R.id.txtClave);

    }
    public void Login(){

        String path = "http://192.168.0.109:8080/usuario/ingresar";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("response","response" + response);

                if(response.trim().equalsIgnoreCase("login-success")){

                    Toast.makeText(getApplicationContext(), "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(MainActivity.this, ActividadAlquiler.class);
                    Bundle enviar = new Bundle();
                    String user = cedula.getText().toString();
                    enviar.putString("cedula", user);
                    intent.putExtras(enviar);
                    startActivity(intent);
                    correo.setText("");
                    cedula.setText("");

                }else{
                    Toast.makeText(getApplicationContext(), "Ingresa todos los datos correctamente", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Ingresa todos los datos", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("correo", correo.getText().toString().trim());
                params.put("cedula", cedula.getText().toString().trim());

                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Cartelera_SingletonVolley.getInstance(this).addRquestque(stringRequest);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrase:
                Intent intent;
                intent = new Intent(MainActivity.this, Registrarse.class);
                startActivity(intent);
                break;
            case R.id.btnIngresar:
                Login();
                break;
        }



    }
}
