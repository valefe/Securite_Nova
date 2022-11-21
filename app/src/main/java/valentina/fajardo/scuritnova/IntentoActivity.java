package valentina.fajardo.scuritnova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class IntentoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LocationManager locationManager;
    private Location currentLocation;
    private DatabaseReference databaseReference;
    private ArrayList<Marker> temporalRealTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intento);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bogota = new LatLng(4.6076185,  -74.0690172);
        mMap.addMarker(new MarkerOptions().position(bogota).title("Universidad Jorge Tadeo Lozano"));//.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_alerta)).anchor(0.0f, 0.0f)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bogota));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(bogota).zoom(12).bearing(90).tilt(45).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        databaseReference.child("marcadores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (Marker dest: realTimeMarkers){
                    dest.remove();
                }
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Marcadores dt = snapshot.getValue(Marcadores.class);
                    Double latitud = dt.getLatitud();
                    Double longitud = dt.getLongitud();
                    String descripcion = dt.getDescripcion();
                    String alertat = "Alerta";
                    String alerta =  descripcion;

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud, longitud)).title(alertat).snippet(alerta);

                    temporalRealTimeMarkers.add(mMap.addMarker(markerOptions));
                }
                realTimeMarkers.clear();
                realTimeMarkers.addAll(temporalRealTimeMarkers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Reportar (View view){
        Intent reportar= new Intent(IntentoActivity.this, ReporteActivity.class);
        startActivity(reportar);
    }
}