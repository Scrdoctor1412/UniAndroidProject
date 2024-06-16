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
import java.util.zip.Inflater;

import phdhtl.k63cntt1.nguyen.R;
import phdhtl.k63cntt1.nguyen.detailsActivity.StoryTypeDetailsActivity;
import phdhtl.k63cntt1.nguyen.helper.DBHelper;
import phdhtl.k63cntt1.nguyen.model.Type;

public class CustomStoryTypeAdapter extends ArrayAdapter<Type> {

    private Activity context;
    private ArrayList<Type> arrayList;
    private int idLayout;
    DBHelper dbh;
    public CustomStoryTypeAdapter(Activity context, int idLayout, ArrayList<Type> arrayList) {
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
        Type mytype = arrayList.get(position);

        TextView txttentl = convertView.findViewById(R.id.txttentl);
        TextView txtndtl = convertView.findViewById(R.id.txtndtl);
        Button btnxoa, btnsua;

        btnxoa = convertView.findViewById(R.id.btnxoatl);
        btnsua = convertView.findViewById(R.id.btnsuatl);

        txttentl.setText(mytype.getTentl());
        txtndtl.setText(mytype.getNoidungtl());

        dbh = new DBHelper(context);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                SQLiteDatabase db = dbh.getWritableDatabase();
                int n = db.delete("types","matl = ?", new String[]{mytype.getMatl()});
                String msg;
                if(n == 0){
                    msg = "Delete unsuccessfully";
                }else{
                    msg = "Delete successfully!";
                }
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                CustomStoryTypeAdapter.this.notifyDataSetChanged();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, StoryTypeDetailsActivity.class);
                myIntent.putExtra("EventCode",2);
                myIntent.putExtra("matl", mytype.getMatl());
                myIntent.putExtra("tentl", mytype.getTentl());
                myIntent.putExtra("ndtl", mytype.getNoidungtl());
                startActivityForResult(context, myIntent, 99,null);
            }
        });
        return convertView;
    }
}
