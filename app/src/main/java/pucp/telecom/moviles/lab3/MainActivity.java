package pucp.telecom.moviles.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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