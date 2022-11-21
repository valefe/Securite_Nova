package valentina.fajardo.scuritnova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReporteActivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    Button consultarLatLong, btnreportar;

    EditText edtLat, edtLong, edtDescrip;

    Spinner spLista;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_reporte);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        consultarLatLong = (Button) findViewById(R.id.obteberubi);
        btnreportar = (Button) findViewById(R.id.enviarrepo);
        edtLat = findViewById(R.id.tctLat);
        edtLong = findViewById(R.id.tctLong);
        edtDescrip = findViewById(R.id.descriopnrep);

        spLista = (Spinner)findViewById(R.id.listaop);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista, android.R.layout.simple_list_item_1);
        spLista.setAdapter(adapter);
        getLocalizacion();
        getCargarLocalizacion();
        GuarDardatos();
        
    }

    private void GuarDardatos() {
        btnreportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud = edtLat.getText().toString().trim();
                String longitud = edtLong.getText().toString().trim();
                String descripcion = edtDescrip.getText().toString().trim();

                if (TextUtils.isEmpty(latitud)){
                    Toast.makeText(getApplicationContext(), "Genere ubicación o verifíque que esté activa en su teléfono", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(longitud)){
                    Toast.makeText(getApplicationContext(), "Genere ubicación o verifíque que esté activa en su teléfono", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(descripcion)){
                    Toast.makeText(getApplicationContext(), "Por favor escribir una descripción", Toast.LENGTH_SHORT).show();
                }else{
                    Marcadores marcadores = new Marcadores(Double.valueOf(latitud), Double.valueOf(longitud), descripcion);
                    databaseReference.child("marcadores").child(descripcion).setValue(marcadores);
                    Toast.makeText(getApplicationContext(), "Información enviada correctamente", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent( ReporteActivity.this, IntentoActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });
    }


    private void getCargarLocalizacion() {
        consultarLatLong.setOnClickListener(new View.OnClickListener() {
            LocationManager locationManager = (LocationManager) ReporteActivity.this.getSystemService(Context.LOCATION_SERVICE);
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager)ReporteActivity.this.getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        edtLat.setText(""+location.getLatitude());
                        edtLong.setText(""+location.getLongitude());
                    }
                    @Override
                    public void  onStatusChanged(String provider, int status, Bundle extras){

                    }
                    @Override
                    public void onProviderEnabled(String provider){

                    }
                    @Override
                    public void onProviderDisabled(String provider){

                    }
                };
                int permiso = ContextCompat.checkSelfPermission(ReporteActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                Toast.makeText( ReporteActivity.this,  "Ubicación generada con exito", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(ReporteActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(ReporteActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)){

            }else {
                ActivityCompat.requestPermissions(ReporteActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
}
