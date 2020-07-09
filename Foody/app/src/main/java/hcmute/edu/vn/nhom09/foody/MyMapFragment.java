package hcmute.edu.vn.nhom09.foody;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

    public MyMapFragment()  {
        getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;

        // Set default position
        // Add a marker in Sydney and move the camera
        LatLng vietnam = new LatLng(Double.parseDouble(QuanAnActivity.diem2_x), Double.parseDouble(QuanAnActivity.diem2_y)); // 14.0583° N, 108.2772° E
        this.googleMap.addMarker(new MarkerOptions().position(vietnam).title(QuanAnActivity.tenQuanAn));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(vietnam));
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vietnam, 16.0f));
        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : "+ latLng.longitude);
                // Clear previously click position.
                googleMap.clear();
                // Zoom the Marker
                googleMap.addMarker(new MarkerOptions().position(latLng).title(QuanAnActivity.tenQuanAn));
                // Add Marker on Map
                googleMap.addMarker(markerOptions);
            }
        });
    }
}