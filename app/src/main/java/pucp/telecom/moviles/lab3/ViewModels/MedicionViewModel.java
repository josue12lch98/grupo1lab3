package pucp.telecom.moviles.lab3.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicionViewModel extends ViewModel {

    private MutableLiveData<Integer> contador = new MutableLiveData<>();

    private MutableLiveData<Integer> contador2 = new MutableLiveData<>();

    public MutableLiveData<Integer> getContador2() {
        return contador2;
    }

    public MutableLiveData<Integer> getContador() {
        return contador;
    }


    private Thread thread;

    public void iniciarMedicion() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                int contadorLocal = 1;

                for (; contadorLocal <= 20; contadorLocal++) {
                    Log.d("contadorVal", String.valueOf(contador.getValue()));
                    contador.postValue(contadorLocal);
                    contador2.postValue(contadorLocal);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        thread.start();
    }

    public void detenerMedicion(){
        thread.interrupt();
    }


}
