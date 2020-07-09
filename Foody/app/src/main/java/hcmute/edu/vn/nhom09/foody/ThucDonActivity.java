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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ThucDonActivity extends AppCompatActivity {

    TextView btnBack;
    TextView foodname;
    ListView listView;
    EditText searchMenu;

    Button btnSearch;

    SQLiteDatabase database;

    ArrayList<ThucDon> list;
    AdapterThucDon adapterMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don);


        addControls();
        initUI();


        searchMenu.addTextChangedListener(new TextWatcher() {
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


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void filter(String text)
    {
        ArrayList<ThucDon> filteredList = new ArrayList<>();
        for (ThucDon item : list){
            if (item.name.toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        adapterMenu.filterList(filteredList);
    }


    private void initUI() {
        list = new ArrayList<>();
        listView = findViewById(R.id.listviewMenu);
        adapterMenu = new AdapterThucDon(this, list);
        listView.setAdapter(adapterMenu);

        Intent intent = getIntent();
        String id = intent.getStringExtra("foody_id_menu");
        database = Database.initDatabase(this, MainActivity.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM menu WHERE menu_id = ?",new String[]{id} );

        list.clear();
        for (int i = 0; i <cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            int idFoody = cursor.getInt(0);
            String idMenu = cursor.getString(1);
            String name = cursor.getString(2);
            String price = cursor.getString(3);
            list.add(new ThucDon(idFoody, idMenu, name, price));

        }
        adapterMenu.notifyDataSetChanged();


        Cursor cursor1 = database.rawQuery("SELECT * FROM foody WHERE menu_id = ?",new String[]{id} );

        cursor1.moveToFirst();
        String name = cursor1.getString(1);
        foodname.setText(name);
    }

    private void addControls() {
        btnBack = findViewById(R.id.textviewBackMenu);
        foodname = findViewById(R.id.txtTitleFoodMenu);

        searchMenu = findViewById(R.id.edittextSearchMenu);
        list = new ArrayList<>();
        listView = findViewById(R.id.listviewMenu);
        adapterMenu = new AdapterThucDon(this, list);
        listView.setAdapter(adapterMenu);
    }
}
