package pucp.telecom.moviles.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pucp.telecom.moviles.lab3.Entidades.Grabacion;

public class GuardarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);
    }

    String latitud;
    String altitud;

    public void GuardarRemotamente(View view){

        final Grabacion grabacion = new Grabacion();

        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permiso == PackageManager.PERMISSION_GRANTED){
            FusedLocationProviderClient fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(this);

                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null){

                                grabacion.setLatitud( String.valueOf(location.getLatitude()));
                                grabacion.setLongitud(String.valueOf(location.getLongitude()));

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            /* Toast toast = new Toast.makeText(getApplicationContext(),"Error al enviar, trate de nuevo",
                                    Toast.LENGTH_SHORT); */
                        }
                    });


        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }




        String url = "http://ec2-34-234-229-191.compute-1.amazonaws.com:5000/saveData";
        StringRequest stringRequest =new StringRequest(StringRequest.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros= new HashMap<>();
                     Gson gson = new Gson();


                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HH");
                Date myDate = new Date();
                String filename = timeStampFormat.format(myDate);
                String nombreJson = "medicion_"+filename;
                    parametros.put("nombreJson",gson.toJson(grabacion));

                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras  = new HashMap<>();
                cabeceras.put("X-Api-Token","y6yeJmYwyRBw2E7bU2iJB8d6fFPv6a");
                cabeceras.put("Content-Type","application/json");
                return cabeceras;
            }
        };


    }


}
