package hcmute.edu.vn.nhom09.foody;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Button btntinhthanh;

    public static String DATABASE_NAME = "foodyDB.db";
    public static SQLiteDatabase database;

    public static ListView listView;
    public static String locationStore;


    ArrayList<QuanAn> list;
    AdapterQuanAn adapterQuanAn;
    EditText btnSearch;
    int REQUSET_CODE_EDIT = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btntinhthanh = findViewById(R.id.buttonTinhThanh);


        addControls();
        readData();

        btnSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        btntinhthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchTinhThanhActivity.class);
                startActivityForResult(intent, REQUSET_CODE_EDIT);
            }
        });
    }

    private void filter(String text)
    {
        ArrayList<QuanAn> filteredList = new ArrayList<>();
        for (QuanAn item : list){
            if (item.food_name.toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        adapterQuanAn.filterList(filteredList);
    }

    private void addControls() {
        listView = findViewById(R.id.recyclerview_id);
        list = new ArrayList<>();
        adapterQuanAn = new AdapterQuanAn(this, list);
        listView.setAdapter(adapterQuanAn);
       btnSearch = findViewById(R.id.edittextSearchStore);
    }

    private void readData(){

        Intent intentTinh = getIntent();
        String nameTinh = intentTinh.getStringExtra("name_tinh");
        if (nameTinh == "" || nameTinh == null)
        {
            btntinhthanh.setText("TP HCM");
        }
        else
        {
            btntinhthanh.setText(nameTinh);
        }

        nameTinh = btntinhthanh.getText().toString().trim();
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM foody WHERE tinhthanh = ?", new String[]{nameTinh});
        list.clear();
        for (int i = 0; i <cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
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

            list.add(new QuanAn(id, name, address, vitri,vitri_vido, tinhThanh, gioMoCua, gioDongCua,gia, menu, wifi, mkwifi, image,image2,image3,image4));

        }
        adapterQuanAn.notifyDataSetChanged();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUSET_CODE_EDIT && resultCode == RESULT_OK && data != null )
        {
            String ten = data.getStringExtra("aaa");
            btntinhthanh.setText(ten);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }








}