package valentina.fajardo.scuritnova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class InfoActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("UN LUGAR SEGURO")
                .setContent("Encuentra las rutas mas seguras de la \n" +
                        "ciudad, con menos reportes de peligro. \n")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider1) // int top drawable
                .build());

        addFragment(new Step.Builder().setTitle("EVITA LOS LUGARES CON \n" +
                        "REPORTES DE PELIGRO")
                .setContent("Te mostraremos los lugares que han \n" +
                        "sido reportados y podrás ver detalle de \n" +
                        "lo sucedido aquí.\n")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider2) // int top drawable
                .build());

        addFragment(new Step.Builder().setTitle("MODO DEMO")
                .setContent("Este modo protegerá tus apps, de esta \n" +
                        "manera proteges tus cuentas bancarias, \n" +
                        "contactos, fotos y en general tu \n" +
                        "información personal creando un espacio \n" +
                        "en el que solo se puede salir desde la \n" +
                        "clave normal de tu celular")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider3) // int top drawable
                .build());

        addFragment(new Step.Builder().setTitle("GRABACIÓN DE LOS HECHOS")
                .setContent("En modo demo tu cámara se activará para \n" +
                        "grabar durante un minuto, este video se \n" +
                        "enviará a la nube que puedes consultar \n" +
                        "por la pagina web en cualquier momento.\n")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider4) // int top drawable
                .build());

        addFragment(new Step.Builder().setTitle("MENSAJE DE PRECAUCIÓN")
                .setContent("Se enviará un mensaje predeterminado \n" +
                        "a tus contactos de emergencia.")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider5) // int top drawable
                .build());

        addFragment(new Step.Builder().setTitle("LLAVE DE SEGURIDAD")
                .setContent("Esta llave será usada cuando tengas la \n" +
                        "oportunidad de activar manualmente el \n" +
                        "modo demo.\n")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider6) // int top drawable
                .build());

        addFragment(new Step.Builder().setTitle("RUTA DE SEGURIDAD")
                .setContent("Crea una ruta fija de un punto A a un \n" +
                        "punto B. si en el proceso se detecta el \n" +
                        "abandono de la ruta se activará el modo \n" +
                        "demo instantáneamente.")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider7) // int top drawable
                .build());

        addFragment(new Step.Builder().setTitle("CONTACTOS DE EMERGENCIA")
                .setContent("Podrás añadir y eliminar contactos a los \n" +
                        "que se les enviará una notificación. ")
                .setBackgroundColor(Color.parseColor("#000000")) // int background color
                .setDrawable(R.drawable.slider8) // int top drawable
                .build());
        setPrevText("ATRÁS"); // Previous button text
        setNextText("SIGUIENTE"); // Next button text
        setFinishText("LISTO"); // Finish button text
        setCancelText("SALIR"); // Cancel button text
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    @Override
    public void finishTutorial() {
        Intent i = new Intent(InfoActivity.this, BienvenidoActivity.class);
        startActivity(i);
        finish();
    }
}

