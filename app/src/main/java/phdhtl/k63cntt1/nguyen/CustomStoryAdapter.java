package phdhtl.k63cntt1.nguyen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.Truyen;

public class CustomStoryAdapter extends ArrayAdapter<Truyen> {
    Activity context;
    int idLayout;
    ArrayList<Truyen> mylist;
    public CustomStoryAdapter(Activity context, int idLayout, ArrayList<Truyen> mylist) {
        super(context, idLayout, mylist);
        this.context = context;
        this.idLayout = idLayout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myflater = context.getLayoutInflater();
        convertView = myflater.inflate(idLayout, null);
        Truyen mytruyen = mylist.get(position);

        ImageView anhbia = convertView.findViewById(R.id.imganhtruyen);
        anhbia.setImageResource(mytruyen.getAnhbia());

        TextView txtmatruyen, txttentruyen, txttheloai, txttacgia;
        txtmatruyen = convertView.findViewById(R.id.txtmatruyen);
        txttentruyen = convertView.findViewById(R.id.txttentruyen);
        txttheloai = convertView.findViewById(R.id.txttheloai);
        txttacgia = convertView.findViewById(R.id.txttacgia);

        txtmatruyen.setText(mytruyen.getMatruyen());
        txttentruyen.setText(mytruyen.getTentruyen());
        txttheloai.setText(mytruyen.getTheloai());
        txttacgia.setText(mytruyen.getTacgia());

        return convertView;
    }
}
