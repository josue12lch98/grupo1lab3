package pucp.telecom.moviles.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import pucp.telecom.moviles.lab3.ViewModels.MedicionViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }




}
