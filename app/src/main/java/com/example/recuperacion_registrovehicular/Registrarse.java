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

public class Registrarse extends AppCompatActivity implements View.OnClickListener {
    Button registro;
    EditText cedula, nombres, apellidos, correo, FN, celular, direccion;
    String host = "http://192.168.0.109:8080";
    String insert = "/usuario/crearUsuario";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        cargarComponentes();
    }

    public void cargarComponentes(){
        registro = findViewById(R.id.buttonR);
        registro.setOnClickListener(this);
        cedula = findViewById(R.id.txtCedula);
        nombres = findViewById(R.id.txtNombres);
        apellidos = findViewById(R.id.txtApellidos);
        correo = findViewById(R.id.txtCorreo);
        FN = findViewById(R.id.txtFechaN);
        celular = findViewById(R.id.txtCelular);
        direccion = findViewById(R.id.txtDireccion);


    }

    public void inseetarPersona(){
        Log.e("response","metodo");
        String path = "http://192.168.0.109:8080/usuario/crearUsuario";;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("response","response" + response);

                if(response.trim().equalsIgnoreCase("succes")){

                    Toast.makeText(getApplicationContext(), "Registrado", Toast.LENGTH_SHORT).show();
                    cedula.setText("");
                    nombres.setText("");
                    apellidos.setText("");
                    correo.setText("");
                    FN.setText("");
                    celular.setText("");
                    direccion.setText("");
                    Intent intent;
                    intent = new Intent(Registrarse.this, MainActivity.class);
                    startActivity(intent);




                }else{
                    Toast.makeText(getApplicationContext(), "Cedula ya registrada", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "LLena los campos", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cedula", cedula.getText().toString().trim());
                params.put("nombres", nombres.getText().toString().trim());
                params.put("apellidos", apellidos.getText().toString().trim());
                params.put("correo", correo.getText().toString().trim());
                params.put("fechaNacimiento", FN.getText().toString().trim());
                params.put("celular", celular.getText().toString().trim());
                params.put("direccion", direccion.getText().toString().trim());

                return  params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Cartelera_SingletonVolley.getInstance(this).addRquestque(stringRequest);

    }

    @Override
    public void onClick(View v) {
inseetarPersona();
    }
}
