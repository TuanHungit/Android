package hcmute.edu.vn.nhom09.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class WifiActivity extends AppCompatActivity {

    TextView btnBack;
    TextView namefood;

    Button btnCapNhat;

    EditText editWifiName, editWifiPass;



    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        addControls();
        initUI();




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String id = intent.getStringExtra("foody_id_menu");

                String wifiname1 = editWifiName.getText().toString().trim();
                String wifipass1 = editWifiPass.getText().toString().trim();

                ContentValues contentValues = new ContentValues();
                contentValues.put("wifi", wifiname1);
                contentValues.put("mkwifi", wifipass1);

                database.update("foody", contentValues, "menu_id = ?",new String[]{id});
                Toast.makeText(WifiActivity.this, "Cập nhật thành công !", LENGTH_SHORT).show();
            }
        });



    }


    private void initUI() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("foody_id_menu");
        database = Database.initDatabase(this, MainActivity.DATABASE_NAME);

        Cursor cursor1 = database.rawQuery("SELECT * FROM foody WHERE menu_id = ?",new String[]{id} );

        cursor1.moveToFirst();
        final int idFoody = cursor1.getInt(0);
        String name = cursor1.getString(1);
        namefood.setText(name);

        String wifiname = cursor1.getString(10);
        String wifipass = cursor1.getString(11);

        editWifiName.setText(wifiname);
        editWifiPass.setText(wifipass);

    }

    private void addControls() {
        btnBack = findViewById(R.id.textviewBackWifi);
        namefood = findViewById(R.id.textviewWifiFoodName);
        editWifiName = findViewById(R.id.edittextwifiname);
        editWifiPass = findViewById(R.id.edittextwifipass);
        btnCapNhat = findViewById(R.id.btnCapNhatWifi);

    }
}
