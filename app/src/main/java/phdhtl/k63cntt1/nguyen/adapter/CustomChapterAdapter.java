package phdhtl.k63cntt1.nguyen.adapter;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.detailsActivity.ChapterDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Chapter;

public class CustomChapterAdapter extends ArrayAdapter<Chapter> {
    private Activity context;
    private ArrayList<Chapter> arrayList;
    private int idLayout;
    DBHelper dbh;

    public CustomChapterAdapter(Activity context, int idLayout, ArrayList<Chapter> arrayList) {
        super(context, idLayout, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        this.idLayout = idLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout,null);
        Chapter mychapter = arrayList.get(position);

        TextView txtchuongso, txttenchuong;
        txttenchuong = convertView.findViewById(R.id.txttenchuong);
        txtchuongso = convertView.findViewById(R.id.txtchuongso);

        txtchuongso.setText("Chương " + mychapter.getChuongso() + ":");
        txttenchuong.setText(mychapter.getChuongten());

        Button btnxoa, btnsua;

        btnsua = convertView.findViewById(R.id.btnsuachapter);
        btnxoa = convertView.findViewById(R.id.btnxoachapter);

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    arrayList.remove(position);
                    SQLiteDatabase db = dbh.getWritableDatabase();
                    int n = db.delete("chapters", "machuong = ?", new String[]{mychapter.getMachuong()});
                    int m = db.delete("chapterimage", "machuong = ?", new String[]{mychapter.getMachuong()});
                    String msg = "";
                    if(n == 0 && m == 0){
                        msg = "No record was deleted";
                    }else{
                        msg = n + " record was deleted";
                    }
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    CustomChapterAdapter.this.notifyDataSetChanged();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, ChapterDetailsActivity.class);
                myIntent.putExtra("EventCode", 2);
                myIntent.putExtra("machuong", mychapter.getMachuong());
                myIntent.putExtra("chuongso", mychapter.getChuongso());
                myIntent.putExtra("chuongten",mychapter.getChuongten());
                startActivityForResult(context,myIntent,99,null);
            }
        });

        dbh = new DBHelper(context);
        return convertView;
    }
}
