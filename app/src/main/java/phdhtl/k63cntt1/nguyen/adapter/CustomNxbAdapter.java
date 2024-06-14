package phdhtl.k63cntt1.nguyen.adapter;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.detailsActivity.NxbDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Publisher;

public class CustomNxbAdapter extends ArrayAdapter<Publisher> {

    ArrayList<Publisher> mylist;
    Activity context;
    int idLayout;
    DBHelper dbh;
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

        Button btnsua = convertView.findViewById(R.id.btnsuanxb);
        Button btnxoa = convertView.findViewById(R.id.btnxoanxb);

        dbh = new DBHelper(context);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.remove(position);
                SQLiteDatabase db = dbh.getWritableDatabase();
                int n = db.delete("publisher", "manxb = ?", new String[]{mynxb.getManxb()});
                String msg = "";
                if(n == 0){
                    msg = "No record was deleted";
                }else{
                    msg = n + " record was deleted";
                }
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                CustomNxbAdapter.this.notifyDataSetChanged();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, NxbDetailsActivity.class);
                myIntent.putExtra("EventCode",2);
                myIntent.putExtra("manxb",mynxb.getManxb());
                myIntent.putExtra("tennxb",mynxb.getTennxb());
                myIntent.putExtra("gthnxb",mynxb.getGioithieu());
                myIntent.putExtra("imgnxb",mynxb.getImgdaidien());

                startActivityForResult(context, myIntent, 99,null);
            }
        });

        return convertView;
    }
}
