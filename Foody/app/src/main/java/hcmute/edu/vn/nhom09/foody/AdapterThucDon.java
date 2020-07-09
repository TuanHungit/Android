package hcmute.edu.vn.nhom09.foody;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterThucDon extends BaseAdapter {

    Activity context;
    ArrayList<ThucDon> list;

    public AdapterThucDon(Activity context, ArrayList<ThucDon> list) {
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
        View row = inflater.inflate(R.layout.cardview_item_menu, null);
        TextView txtName = row.findViewById(R.id.food_title_foodname);
        TextView txtPrice = row.findViewById(R.id.food_price);

        final ThucDon menu = list.get(position);
        txtName.setText("  " + menu.name);
        txtPrice.setText("  " + menu.price);
       return row;
    }

    public void filterList(ArrayList<ThucDon> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}