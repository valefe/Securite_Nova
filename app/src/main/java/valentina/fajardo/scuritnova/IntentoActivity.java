package valentina.fajardo.scuritnova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class IntentoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intento);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bogota = new LatLng(4.6076185,  -74.0690172);
        mMap.addMarker(new MarkerOptions().position(bogota).title("Universidad Jorge Tadeo Lozano"));//.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_alerta)).anchor(0.0f, 0.0f)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bogota));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(bogota).zoom(18).bearing(90).tilt(45).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    public void Reportar (View view){
        Intent reportar= new Intent(IntentoActivity.this, ReporteActivity.class);
        startActivity(reportar);
    }
}