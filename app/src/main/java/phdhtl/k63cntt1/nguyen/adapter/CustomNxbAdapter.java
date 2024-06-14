package phdhtl.k63cntt1.nguyen.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.model.Publisher;

public class CustomNxbAdapter extends ArrayAdapter<Publisher> {

    ArrayList<Publisher> mylist;
    Activity context;

    int idLayout;
    public CustomNxbAdapter(Activity context, int idLayout, ArrayList<Publisher> mylist) {
        super(context, idLayout, mylist);
        this.context = context;
        this.idLayout = idLayout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = context.getLayoutInflater();
        convertView = myInflater.inflate(idLayout, null);
        Publisher mynxb = mylist.get(position);

        ImageView imgdaidien = convertView.findViewById(R.id.imgdaidiennxb);
        imgdaidien.setImageBitmap(ConvertHelper.StringToBitMap(mynxb.getImgdaidien()));

        TextView txtmanxb, txttennxb, txtgioithieu;
        txtmanxb = convertView.findViewById(R.id.txtmanxb);
        txttennxb = convertView.findViewById(R.id.txttennxb);
        txtgioithieu = convertView.findViewById(R.id.txtgioithieunxb);

        txtmanxb.setText(mynxb.getManxb());
        txttennxb.setText(mynxb.getTennxb());
        txtgioithieu.setText(mynxb.getGioithieu());
        return convertView;
    }
}
