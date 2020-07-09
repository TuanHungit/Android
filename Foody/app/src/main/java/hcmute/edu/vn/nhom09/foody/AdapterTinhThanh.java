package hcmute.edu.vn.nhom09.foody;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterTinhThanh extends BaseAdapter {

    Activity context;
    ArrayList<TinhThanh> list;

    public AdapterTinhThanh(Activity context, ArrayList<TinhThanh> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.cardview_item_tinhthanh, null);

        TextView txtName = row.findViewById(R.id.textviewtinhThanh);

        final TinhThanh tinhThanh = list.get(position);
        txtName.setText("  " + tinhThanh.name);


        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("name_tinh",tinhThanh.name);
                context.startActivity(intent);
            }
        });

        return row;
    }
    public void filterList(ArrayList<TinhThanh> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}
