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
import phdhtl.k63cntt1.nguyen.detailsActivity.StoryDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.ConvertHelper;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Story;

public class CustomStoryAdapter extends ArrayAdapter<Story> {
    Activity context;
    int idLayout;
    ArrayList<Story> mylist;
    public CustomStoryAdapter(Activity context, int idLayout, ArrayList<Story> mylist) {
        super(context, idLayout, mylist);
        this.context = context;
        this.idLayout = idLayout;
        this.mylist = mylist;
    }

    DBHelper dbh;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myflater = context.getLayoutInflater();
        convertView = myflater.inflate(idLayout, null);
        Story mytruyen = mylist.get(position);

        ImageView anhbia = convertView.findViewById(R.id.imganhtruyen);
//        anhbia.setImageResource(mytruyen.getAnhbia());
        anhbia.setImageBitmap(ConvertHelper.StringToBitMap(mytruyen.getAnhbia()));

        TextView txtmatruyen, txttentruyen, txttheloai, txttacgia;
        txtmatruyen = convertView.findViewById(R.id.txtmatruyen);
        txttentruyen = convertView.findViewById(R.id.txttentruyen);
        txttheloai = convertView.findViewById(R.id.txttheloai);
        txttacgia = convertView.findViewById(R.id.txttacgia);

        txtmatruyen.setText(mytruyen.getMatruyen());
        txttentruyen.setText(mytruyen.getTentruyen());
        txttheloai.setText(mytruyen.getTheloai());

        txttacgia.setText(mytruyen.getTacgia());

        Button btnxoa, btnsua;

        btnxoa = convertView.findViewById(R.id.btnxoastory);
        btnsua = convertView.findViewById(R.id.btnsuastory);

        dbh = new DBHelper(context);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.remove(position);
                SQLiteDatabase db = dbh.getWritableDatabase();
                int n = db.delete("stories", "matruyen = ?", new String[]{mytruyen.getMatruyen()});
                int m = db.delete("typestory", "matruyen = ?", new String[]{mytruyen.getMatruyen()});
                String msg = "";
                if(n == 0 && m == 0){
                    msg = "No record was deleted!";
                }else{
                    msg = n + " was deleted";
                }
                Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
                CustomStoryAdapter.this.notifyDataSetChanged();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, StoryDetailsActivity.class);
                myIntent.putExtra("EventCode", 2);
                myIntent.putExtra("matruyen", mytruyen.getMatruyen());
                myIntent.putExtra("tentruyen", mytruyen.getTentruyen());
                myIntent.putExtra("noidung", mytruyen.getNoidung());
                myIntent.putExtra("theloai", mytruyen.getTheloai());
                myIntent.putExtra("anhbia", mytruyen.getAnhbia());
                myIntent.putExtra("luotlike",mytruyen.getLuotlike());
                myIntent.putExtra("luotxem",mytruyen.getLuotxem());
                startActivityForResult(context, myIntent, 99,null);
            }
        });

        return convertView;
    }
}
