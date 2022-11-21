package valentina.fajardo.scuritnova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ReporteActivity extends AppCompatActivity {
    Button reportar;
    Spinner spLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        getSupportActionBar().setCustomView(R.layout.txt_reporte);

        spLista = (Spinner)findViewById(R.id.listaop);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista, android.R.layout.simple_list_item_1);
        spLista.setAdapter(adapter);
        getLocalizacion();
    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
}