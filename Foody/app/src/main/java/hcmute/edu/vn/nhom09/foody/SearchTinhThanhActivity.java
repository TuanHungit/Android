package hcmute.edu.vn.nhom09.foody;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SearchTinhThanhActivity extends AppCompatActivity {


    EditText editText;
    TextView txtXong, txtHuy;
    public static String giatridachon, ganTinh;

    SQLiteDatabase database;
    ListView listView;
    ArrayList<TinhThanh> list;
    AdapterTinhThanh adapterTinhThanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tinh_thanh);

        addControls();
        readData();




        editText.addTextChangedListener(new TextWatcher() {
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






        txtHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        listView = (ListView) findViewById(R.id.listview);
        editText = (EditText) findViewById(R.id.edittextSearch);
        txtHuy = findViewById(R.id.textviewHuy);


        list = new ArrayList<>();
        adapterTinhThanh = new AdapterTinhThanh(this, list);
        listView.setAdapter(adapterTinhThanh);

    }


    private void readData(){
        database = Database.initDatabase(this, MainActivity.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM tinhThanh", null);
        list.clear();
        for (int i = 0; i <cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String name= cursor.getString(1);
            list.add(new TinhThanh(id, name));

        }
        adapterTinhThanh.notifyDataSetChanged();
    }

    private void filter(String text)
    {
        ArrayList<TinhThanh> filteredList = new ArrayList<>();
        for (TinhThanh item : list){
            if (item.name.toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        adapterTinhThanh.filterList(filteredList);
    }



}
