package pucp.telecom.moviles.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.content.ContextCompat;

import android.media.MediaRecorder;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

import pucp.telecom.moviles.lab3.ViewModels.MedicionViewModel;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    private final double referenceAmplitude = 0.0001;
    MediaRecorder myAudioRecorder=new MediaRecorder();
    Button buttonIniciarMedicion = (Button) findViewById(R.id.button);
    Button buttonDetenerMedicion = (Button) findViewById(R.id.button2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        buttonIniciarMedicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                myAudioRecorder.setOutputFile("/dev/null");

                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Medición Iniciada", Toast.LENGTH_LONG).show();
                buttonIniciarMedicion.setEnabled(false);
                buttonDetenerMedicion.setEnabled(true);
                final double maxAmplitude = myAudioRecorder.getMaxAmplitude();
                final double potencia = 20 * Math.log10(maxAmplitude / referenceAmplitude);

            }

        final TextView textViewContador = findViewById(R.id.ViewRuido);

        MedicionViewModel contadorViewModel = new ViewModelProvider(this).get(MedicionViewModel.class);

        contadorViewModel.getContador().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textViewContador.setText(String.valueOf(integer));
            }});
    }


    public void iniciarMedicion (){

        MedicionViewModel contadorViewModel = new ViewModelProvider(this).get(MedicionViewModel.class);
        contadorViewModel.iniciarMedicion();


    }
    public  void detenerMedicion(){
        MedicionViewModel contadorViewModel = new ViewModelProvider(this).get(MedicionViewModel.class);
        contadorViewModel.detenerMedicion();}


        });

        buttonDetenerMedicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myAudioRecorder.stop();
                buttonDetenerMedicion.setEnabled(false);
                buttonIniciarMedicion.setEnabled(true);
                Toast.makeText(MainActivity.this, "Medición Detenida", Toast.LENGTH_LONG).show();

            }
        });

    }
}

    }




}