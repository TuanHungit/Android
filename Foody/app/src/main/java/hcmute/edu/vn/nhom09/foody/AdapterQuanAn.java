package hcmute.edu.vn.nhom09.foody;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterQuanAn extends BaseAdapter {

    Activity context;
    ArrayList<QuanAn> list;


    public AdapterQuanAn(Activity context, ArrayList<QuanAn> list) {
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
    public View getView(int position, final View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.cardview_item_food, null);
        ImageView image = row.findViewById(R.id.food_img);
        TextView txtName = row.findViewById(R.id.food_title);
        TextView txtAddress = row.findViewById(R.id.food_address);

        final QuanAn foody = list.get(position);
        txtName.setText("  " + foody.food_name);
        txtAddress.setText("  " + foody.address + ", " + foody.tinhthanh);

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(foody.image, 0, foody.image.length);
        image.setImageBitmap(bmHinhDaiDien);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuanAnActivity.class);
                intent.putExtra("foody_id",foody.food_id);
                context.startActivity(intent);
            }
        });

        return row;
    }

    public void filterList(ArrayList<QuanAn> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }


}
