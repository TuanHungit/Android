package hcmute.edu.vn.nhom09.foody;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class QuanAnActivity extends AppCompatActivity {


    TextView thucdon, themwifi,password,btnBack,foodname,thoigian,dongcua,diachi, location, giatien;
    ImageView imageDaiDien,imageDaiDien2,imageDaiDien3,imageDaiDien4;
    Button btnLienHe;
    Random rd;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    public static String diem1_x, diem1_y, diem2_x, diem2_y,tenQuanAn;

    private MyMapFragment myMapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_an);
        rd = new Random();
        btnBack = findViewById(R.id.textviewBack);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.myMapFragment = (MyMapFragment) fragmentManager.findFragmentById(R.id.myMap);


        addControls();
        init();

        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    QuanAnActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            getCurrentLocation();
        }
        btnLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = new ArrayList<String>() {{
                    add("0355243256");
                    add("0371267256");
                    add("0398967256");
                    add("0325237256");
                } };
                int number1 = rd.nextInt(4);
                String dial = "tel:" + list.get(number1);
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("foody_id", 0);
        Cursor cursor = MainActivity.database.rawQuery("SELECT * FROM foody WHERE food_id = ?  ",new String[]{id + ""} );
        cursor.moveToFirst();
        final int idFoody = cursor.getInt(0);
        String name= cursor.getString(1);
        String address = cursor.getString(2);
        String vitri = cursor.getString(3);
        String vitri_vido = cursor.getString(4);
        String tinhThanh = cursor.getString(5);
        String gioMoCua = cursor.getString(6);
        String gioDongCua = cursor.getString(7);
        String gia = cursor.getString(8);
        final String menu = cursor.getString(9);
        String wifi = cursor.getString(10);
        String mkwifi = cursor.getString(11);
        byte[] image = cursor.getBlob(12);
        byte[] image2 = cursor.getBlob(13);
        byte[] image3 = cursor.getBlob(14);
        byte[] image4 = cursor.getBlob(15);

        diem2_x = vitri;
        diem2_y = vitri_vido;

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageDaiDien.setImageBitmap(bmHinhDaiDien);

        Bitmap bmHinhDaiDien2 = BitmapFactory.decodeByteArray(image2, 0, image2.length);
        imageDaiDien2.setImageBitmap(bmHinhDaiDien2);

        Bitmap bmHinhDaiDien3 = BitmapFactory.decodeByteArray(image3, 0, image3.length);
        imageDaiDien3.setImageBitmap(bmHinhDaiDien3);

        Bitmap bmHinhDaiDien4 = BitmapFactory.decodeByteArray(image4, 0, image4.length);
        imageDaiDien4.setImageBitmap(bmHinhDaiDien4);
        tenQuanAn = name;
        foodname.setText(name);
        thoigian.setText("Thời gian hoạt động: "+gioMoCua + "h - " + gioDongCua+"h");
        diachi.setText(address + ", " + tinhThanh);

        giatien.setText(gia);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String getCurrentDateTime = sdf.format(c.getTime());

        if (getCurrentDateTime.compareTo(gioMoCua) > 0 && getCurrentDateTime.compareTo(gioDongCua) < 0)
        {
            dongcua.setText("Đang mở cửa");
            dongcua.setTextColor(Color.BLUE);
        }
        if(wifi!=null){
            themwifi.setText("Wifi: "+ wifi);
            password.setText("Password: "+ mkwifi);
        }
        thucdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanAnActivity.this, ThucDonActivity.class);
                intent.putExtra("foody_id_menu",menu);
                QuanAnActivity.this.startActivity(intent);
            }
        });

        themwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanAnActivity.this, WifiActivity.class);
                intent.putExtra("foody_id_menu",menu);
                QuanAnActivity.this.startActivity(intent);
            }
        });


   }

    private void addControls() {
        foodname = findViewById(R.id.txtTenQuan);
        thoigian = findViewById(R.id.txtThoiGian);
        dongcua = findViewById(R.id.txtDongCua);

        diachi = findViewById(R.id.txtDiaChi);
        location = findViewById(R.id.txtViTri);
        giatien = findViewById(R.id.txtGiaTien);
        btnLienHe = findViewById(R.id.btnLienHe);
        thucdon = findViewById(R.id.txtThucDon);
        themwifi = findViewById(R.id.txtThemWifi);
        password = findViewById(R.id.txtPassword);
        imageDaiDien = findViewById(R.id.food_img1);
        imageDaiDien2 = findViewById(R.id.food_img2);
        imageDaiDien3 = findViewById(R.id.food_img3);
        imageDaiDien4 = findViewById(R.id.food_img4);
    }

    private void getCurrentLocation() {
        //progressBar.setVisibility(View.VISIBLE);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(QuanAnActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(QuanAnActivity.this).removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude1 = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude1 = locationResult.getLocations().get(latestLocationIndex).getLongitude();

                            diem1_x = String.valueOf(latitude1);
                            diem1_y = String.valueOf(longitude1);

                            float lt1 = Float.parseFloat(diem1_x);
                            float lg1 = Float.parseFloat(diem1_y);

                            float lt2 = Float.parseFloat(diem2_x);
                            float lg2 = Float.parseFloat(diem2_y);

                            String dise = meterDistanceBetweenPoints(lt1,lg1,lt2,lg2);

                            location.setText(
                                    String.format(
                                            "%s Km (Tính từ vị trí của bạn)",
                                            dise
                                    )
                            );
                            location.setTextColor(Color.GREEN);

                        } else {
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                }, Looper.getMainLooper());
    }


    private String meterDistanceBetweenPoints(float lat_a, float lng_a, float lat_b, float lng_b) {
        float pk = (float) (180.f/Math.PI);
        float kinhdo1 = lat_a / pk;
        float vido1 = lng_a / pk;
        float kinhdo2 = lat_b / pk;
        float vido2 = lng_b / pk;
        double t1 = Math.cos(kinhdo1) * Math.cos(vido1) * Math.cos(kinhdo2) * Math.cos(vido2);
        double t2 = Math.cos(kinhdo1) * Math.sin(vido1) * Math.cos(kinhdo2) * Math.sin(vido2);
        double t3 = Math.sin(kinhdo1) * Math.sin(kinhdo2);
        double tt = Math.acos(t1 + t2 + t3);

        double a = 6366000 * tt /1000;
        DecimalFormat f = new DecimalFormat("##.0");

        String t = String.valueOf(f.format(a));

        return t;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
